package cz.macha.spring.dao;

import cz.macha.spring.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerDao extends JpaRepository<Organizer, Integer> {
}
