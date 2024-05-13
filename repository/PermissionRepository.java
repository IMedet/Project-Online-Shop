package kz.medet.onlineshop.repository;

import kz.medet.onlineshop.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByPermission(String permission);

    List<Permission> findAllById(Long id);
}
