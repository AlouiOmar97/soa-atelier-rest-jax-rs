package tn.esprit.atelierrest1.utilities;

import tn.esprit.atelierrest1.resources.EtudiantResource;
import tn.esprit.atelierrest1.resources.OptionResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        classes.add(OptionResource.class);
        classes.add(EtudiantResource.class);

        return classes;
    }
}