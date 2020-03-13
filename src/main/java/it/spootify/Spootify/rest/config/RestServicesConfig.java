package it.spootify.Spootify.rest.config;

import java.util.HashSet;
import java.util.Set;

import it.spootify.Spootify.rest.controller.AlbumController;
import it.spootify.Spootify.rest.controller.ArtistaController;
import it.spootify.Spootify.rest.controller.BranoController;
import it.spootify.Spootify.rest.controller.LoginController;
import it.spootify.Spootify.rest.controller.PlaylistController;
import it.spootify.Spootify.rest.controller.RiproduzioneController;
import it.spootify.Spootify.rest.controller.UtenteController;

public class RestServicesConfig {
	
	public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(UtenteController.class);
        classes.add(LoginController.class);
        classes.add(AlbumController.class);
        classes.add(ArtistaController.class);
        classes.add(BranoController.class);
        classes.add(RiproduzioneController.class);
        classes.add(PlaylistController.class);


        return classes;
	}

}
