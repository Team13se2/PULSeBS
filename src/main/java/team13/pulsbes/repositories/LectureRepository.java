package team13.pulsbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team13.pulsbes.entities.Lecture;

@Repository
public interface LectureRepository extends JpaRepository <Lecture, String> {
}
