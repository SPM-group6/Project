package com.hwadee.core.service.impl;

import com.hwadee.entity.JwtCrew;
import com.hwadee.entity.Crew;
import com.hwadee.core.repository.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CrewRepository crewRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Crew crew = crewRepository.queryCrewById(Integer.parseInt(id)).get(0);
        return new JwtCrew(crew);
    }

}
