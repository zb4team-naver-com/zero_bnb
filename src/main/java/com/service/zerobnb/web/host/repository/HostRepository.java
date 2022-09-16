package com.service.zerobnb.web.host.repository;

import com.service.zerobnb.web.host.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host,Long> {
}
