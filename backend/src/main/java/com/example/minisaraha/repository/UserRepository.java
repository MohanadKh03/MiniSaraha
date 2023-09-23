package com.example.minisaraha.repository;

import com.example.minisaraha.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<User,String> {
}
