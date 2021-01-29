package com.crud.CRUDSpring.interfaceService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.crud.CRUDSpring.entity.Authority;

public interface IfServiceAuthority {
	public List<Authority> listarAuthorities();
	public Optional<Authority> AuthorityPorId(int id);
	public int guardarAuthority(Authority authority);
	public void borrarAuthority(int id);

}
