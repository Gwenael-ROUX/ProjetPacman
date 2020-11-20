package Generique.Moteur;

import javafx.scene.image.ImageView;

public abstract class GraphicsComponent {
    private ImageView imageView;
    private AnimationManager animation;


    abstract void update(Entity entity);
}
