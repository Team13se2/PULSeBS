package team13.pulsbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team13.pulsbes.entities.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,String> {
}
