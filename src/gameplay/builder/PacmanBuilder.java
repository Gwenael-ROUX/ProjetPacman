package gameplay.builder;

import gameplay.LevelGenerator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteurs.Position;
import moteurs.controllers.KeyboardController;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PacmanBuilder extends EntityCharacterBuilder {
    @Override
    public void buildPosition(Position position) {
        entityCharacter.setPosition(position);
    }

    @Override
    public void buildCollider(Collider collider) {
        entityCharacter.setCollider(collider);
    }

    @Override
    public void buildName() {
        entityCharacter.setName("pacman");
    }

    @Override
    public void buildImageView() {
        entityCharacter.setImageView(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/pacman/Pacman.png"))));
    }

    @Override
    public void buildController() {
        entityCharacter.setController(new KeyboardController());
    }

    @Override
    public void buildPhysics() {
        entityCharacter.setPhysics(new Physics(10, 1));
    }

    @Override
    public void buildAnimation() {
        HashMap<String, List<ImageView>> animations = new HashMap<>();
        ImageView pacmanclose = new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/pacman/pacmanClose.png")));

        animations.put("up", new ArrayList<ImageView>());
        animations.get("up").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/pacman/pacmanUp.png"))));
        animations.get("up").add(pacmanclose);

        animations.put("down", new ArrayList<ImageView>());
        animations.get("down").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/pacman/pacmanDown.png"))));
        animations.get("down").add(pacmanclose);


        animations.put("rigth", new ArrayList<ImageView>());
        animations.get("right").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/pacman/pacmanRight.png"))));
        animations.get("right").add(pacmanclose);

        animations.put("left", new ArrayList<ImageView>());
        animations.get("left").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/pacman/pacmanLeft.png"))));
        animations.get("left").add(pacmanclose);

        entityCharacter.setListAnimation(animations);
    }
}
