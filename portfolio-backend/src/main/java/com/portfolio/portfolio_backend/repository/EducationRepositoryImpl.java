package com.portfolio.portfolio_backend.repository;

import com.portfolio.portfolio_backend.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements IEducationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Education> educationRowMapper = (rs, numRow) -> {
        Education education = new Education();
        education.setId(rs.getLong("id"));
        education.setDegree(rs.getString("degree"));
        education.setInstitution(rs.getString("institution"));
        education.setStartDate(rs.getObject("start_date",LocalDate.class));
        education.setEndDate(rs.getObject("end_date", LocalDate.class));
        education.setDescription(rs.getString("description"));
        education.setPersonalInfoId(rs.getLong("personal_info_id"));
        return education;
    };

    @Override
    public Education save(Education education) {
        if(education.getId()==null) {
            String sql = "insert into educations (degree, institution, start_date, end_date, description, personal_info_id) " +
                    "values (?,?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, education.getDegree());
                ps.setString(2, education.getInstitution());
                ps.setObject(3, education.getStartDate());
                ps.setObject(4, education.getEndDate());
                ps.setObject(5, education.getDescription());
                ps.setLong(6, education.getPersonalInfoId());
                return ps;
            }, keyHolder);
            education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        }else{
            String sql = "update educations set degree = ?, institution = ?, start_date = ?, end_date = ?," +
                    "description = ?, personal_info_id = ? where id = ?";
            jdbcTemplate.update(sql,
                    education.getDegree(),
                    education.getInstitution(),
                    education.getStartDate(),
                    education.getEndDate(),
                    education.getDescription(),
                    education.getPersonalInfoId(),
                    education.getId()
                    );
        }
        return education;
    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql = "select * from educations where id = ?";
        try{
            return Optional.of(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "select * from educations";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from educations where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        String sql = "select * from educations where personal_info_id = ?";
        return jdbcTemplate.query(sql, educationRowMapper, personalInfoId);
    }
}
