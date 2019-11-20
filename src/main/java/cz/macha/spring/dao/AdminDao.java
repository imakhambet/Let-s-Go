package cz.macha.spring.dao;

import cz.macha.spring.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Integer> {
}
