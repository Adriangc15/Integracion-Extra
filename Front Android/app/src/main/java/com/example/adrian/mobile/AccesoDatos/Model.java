package com.example.adrian.mobile.AccesoDatos;

import com.example.adrian.mobile.Models.EstudianteModel;
import com.example.adrian.mobile.Models.UserModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {

    private UserModel loggedUser;
    private ArrayList<UserModel> users;
    private ArrayList<EstudianteModel> estudiantes;
    private ArrayList<EstudianteModel> carreras;

    public Model(UserModel loggedUser, ArrayList<UserModel> users, ArrayList<EstudianteModel> estudiantes, ArrayList<EstudianteModel> carreras){
        this.loggedUser = loggedUser;
        this.users = users;
        this.estudiantes = estudiantes;
        this.carreras = carreras;
    }

    public Model(){
        this.loggedUser = null;
        this.initUsers();
        this.initCursos();
        this.initCarreras();
    }

    public UserModel getLoggedUser(){
        return this.loggedUser;
    }

    public UserModel setLoggedUser(UserModel loggedUser){
        this.loggedUser = loggedUser;
        return this.loggedUser;
    }

    public ArrayList<UserModel> getUsers() {
        return this.users;
    }

    public  UserModel getUser(int index){
        return this.users.get(index % this.users.size());
    }

    public UserModel addUser(UserModel user){
        this.users.add(user);
        return user;
    }

    public ArrayList<EstudianteModel> getCursos(){
        return this.estudiantes;
    }

    public EstudianteModel getCurso(int index){
        return this.getCursoIndex(index);
    }

    public EstudianteModel addEstudiante(EstudianteModel curso){
        this.estudiantes.add(curso);
        return curso;
    }

    public ArrayList<EstudianteModel> getCarreras(){
        return this.carreras;
    }

    public EstudianteModel getCarrera(int index){
        return this.carreras.get(index % this.carreras.size());
    }

    public EstudianteModel addCarrera(EstudianteModel carrera){
        this.carreras.add(carrera);
        return carrera;
    }

    private EstudianteModel getCursoIndex(int index){
        return this.estudiantes.get(index % this.estudiantes.size());
    }

    private void initUsers(){
        this.users = new ArrayList<UserModel>(){
            {
                add(new UserModel("adriangc", "Adrian Ch.", "adrian@gmail.com", "adrian123", 1));
                add(new UserModel("antonio","Antonio Q.","antonio@gmail.com","antonio123",1));
            }
        };
    }

    private void initCursos(){
        this.estudiantes = new ArrayList<EstudianteModel>(){
            {
                //add(new EstudianteModel(1, 4, 5,"Programación 1"));
                //add(new EstudianteModel(2, 3, 4,"Inglés 3"));
                //add(new EstudianteModel(3, 5, 5,"Diseño Movil"));
            }
        };
    }

    private void initCarreras(){
        ArrayList<EstudianteModel> carreras = new ArrayList<EstudianteModel>();
        EstudianteModel curso = new EstudianteModel();

        /* Creacion de la primer carrera
        EstudianteModel carrera = new EstudianteModel(1, "Ingenieria","Bachillerato");
        curso = this.getCursoIndex(0);
        curso.setAnho("Primero").setCiclo("Ciclo II");
        carrera.addCurso(curso);

        curso = this.getCursoIndex(2);
        curso.setAnho("Segundo").setCiclo("Ciclo I");
        carrera.addCurso(curso);
        carreras.add(carrera);

        // Creacion de la segunda carrera
        carrera = new EstudianteModel(2, "Ingles", "Bachillerato");
        curso = this.getCursoIndex(1);
        curso.setAnho("Tercer").setCiclo("Ciclo I");
        carrera.addCurso(curso);
        carreras.add(carrera);*/

        this.carreras = carreras;
    }
}
