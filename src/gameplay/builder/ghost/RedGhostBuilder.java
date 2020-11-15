package gameplay.builder.ghost;

import gameplay.LevelGenerator;
import gameplay.builder.EntityCharacterBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteurs.Position;
import moteurs.controllers.ai.AI;
import moteurs.controllers.ai.RandomAI;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RedGhostBuilder extends EntityCharacterBuilder {
    private AI ai;

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
        entityCharacter.setName("ghost");
    }

    @Override
    public void buildImageView() {
        entityCharacter.setImageView(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostRed.png"))));
    }

    @Override
    public void buildController() {
        entityCharacter.setController(ai);
    }

    public void setAI(AI ai){
        this.ai = ai;
    }

    @Override
    public void buildPhysics() {
        entityCharacter.setPhysics(new Physics(30, 1));
    }

    @Override
    public void buildAnimation() {
        HashMap<String, List<ImageView>> animations = new HashMap<>();

        animations.put("up", new ArrayList<ImageView>());
        animations.get("up").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostRed.png"))));

        animations.put("down", new ArrayList<ImageView>());
        animations.get("down").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostRed.png"))));


        animations.put("right", new ArrayList<ImageView>());
        animations.get("right").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostRed.png"))));

        animations.put("left", new ArrayList<ImageView>());
        animations.get("left").add(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostRed.png"))));

        entityCharacter.setListAnimation(animations);
    }
}
