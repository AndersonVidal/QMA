package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.Aluno;

public class Utils {
	
	public static List<Aluno> toList(Map<String, Aluno> c) {
		
		List<Aluno> alunos = new ArrayList<>();
		for(Aluno aluno: c.values()) {
			alunos.add(aluno);
		}
		
		
		return alunos;
		
	}
	
	
}
