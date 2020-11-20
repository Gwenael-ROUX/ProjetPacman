package Generique.Moteur;

import javafx.scene.image.ImageView;

public abstract class GraphicsComponent {
    private ImageView imageView;
    private AnimationManager animation;

    public GraphicsComponent(ImageView imageView) {
        this.imageView = imageView;
    }

    abstract void update(Entity entity);
}
