package com.crud.CRUDSpring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;

@Repository
public interface interfaceAsistencia extends CrudRepository<Asistencia, Integer> {

        // public static final String numeroAsistencias = ("SELECT COUNT(*) FROM
        // asistencia a INNER JOIN horario_dia hd ON a.id_horario = hd.id_horario INNER
        // JOIN dia_de_practica d ON hd.id_dia = d.id_dia WHERE d.dia_de_la_semana =
        // :nombreDia AND a.fecha_asistencia = :fecha ");
        // public static final String numeroHorarios = ("SELECT COUNT(*) FROM
        // horario_dia hd INNER JOIN dia_de_practica d ON hd.id_dia = d.id_dia WHERE
        // d.dia_de_la_semana = :nombreDia ");
        // public static final String nombreDias = ("SELECT d.dia_de_la_semana FROM
        // horario_dia hd INNER JOIN dia_de_practica d ON hd.id_dia = d.id_dia WHERE
        // d.dia_de_la_semana = :nombreDia");

        public Optional<Asistencia> findByFechaAsistenciaInAndProfesorInAndClase(LocalDate date, Profesor profesor,
                        Clase clase);

        public List<Asistencia> findByClase(Clase clase);
        
        public List<Asistencia> findByClaseAndProfesor(Clase clase, Profesor profesor);
        public int countByClaseAndProfesor(Clase clase, Profesor profesor);

        public static String maskDay(DayOfWeek dayOfWeek) {
                switch (dayOfWeek) {
                case MONDAY:
                        return "Lunes";

                case TUESDAY:
                        return "Martes";

                case WEDNESDAY:
                        return "Miercoles";

                case THURSDAY:
                        return "Jueves";

                case FRIDAY:
                        return "Viernes";

                case SATURDAY:
                        return "Sabado";

                case SUNDAY:
                        return "Domingo";
                default:
                        return null;
                }
        }

        // public List<Asistencia> findByHorarioInAndProfesor(Horario horario, Profesor
        // profesor);

        // @Query(value = numeroAsistencias, nativeQuery = true)
        // public int contarNumeroDeAsistenciasPorFechaYNombreDia(@Param("fecha") String
        // fecha,
        // @Param("nombreDia") String nombreDia);

        // @Query(value = numeroHorarios, nativeQuery = true)
        // public int contarCantidadDeHorarios(@Param("nombreDia") String nombreDia);
}
