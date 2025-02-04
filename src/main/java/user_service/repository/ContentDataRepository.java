package user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user_service.entity.ContentData;

@Repository
public interface ContentDataRepository extends JpaRepository<ContentData, Long> {
}
