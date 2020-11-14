package gameplay;import moteurs.Entity;import moteurs.Position;import moteurs.physics.BoxCollider;import moteurs.physics.Displacement;import java.util.ArrayList;import java.util.TimerTask;public class PacmanGame extends TimerTask {    private ArrayList<Entity> entities;    private ArrayList<EntityCharacter> characters;    private Position initMapLimit;    private Position mapLimit;    public PacmanGame(Position mapLimit, ArrayList<Entity> behaviours){        this.entities = behaviours;        initMapLimit = new Position(0, 0);        this.mapLimit = mapLimit;        this.characters = new ArrayList<>();    }    @Override    public void run() {        Position[] prevDisp = new Position[entities.size()];        // Deplacements        for(int i = 0; i < entities.size(); i++){            if (!entities.get(i).getName().equals("mur")){                EntityCharacter entityCharacter = (EntityCharacter) entities.get(i);                characters.add(entityCharacter);                Displacement displacement = entityCharacter.getController().move();                double xDisp = 0, yDisp = 0;                prevDisp[i] = new Position(entityCharacter.getPosition().getX(), entityCharacter.getPosition().getY());                switch (displacement){                    case UP:                        yDisp -= entityCharacter.getPhysics().getSpeed();                        entityCharacter.playAnimation("up");                        break;                    case DOWN:                        yDisp += entityCharacter.getPhysics().getSpeed();                        entityCharacter.playAnimation("down");                        break;                    case LEFT:                        xDisp -= entityCharacter.getPhysics().getSpeed();                        entityCharacter.playAnimation("left");                        break;                    case RIGHT:                        xDisp += entityCharacter.getPhysics().getSpeed();                        entityCharacter.playAnimation("right");                        break;                    default:                        break;                }                entityCharacter.update(new Position(prevDisp[i].getX()+xDisp, prevDisp[i].getY()+yDisp));            }        }        // Gestion des collisions        // TODO : Change collision management (bug : Pacman can move on a Ghost)        // TODO : Add collision management between Ghost and Pacman (with and without bonus)        for(int i = 0; i < entities.size()-1; i++){            for(int j = i+1; j < entities.size(); j++){                // TODO : Suppress cast in the future                if(entities.get(i).getCollider().hit((BoxCollider) entities.get(j).getCollider())){                    EntityCharacter e;                    if (!entities.get(i).getName().equals("mur")){                        e = (EntityCharacter) entities.get(i);                        e.update(new Position(prevDisp[i].getX(), prevDisp[i].getY()));                    }                    if (!entities.get(j).getName().equals("mur")){                        e = (EntityCharacter) entities.get(j);                        e.update(new Position(prevDisp[j].getX(), prevDisp[j].getY()));                    }                }            }        }        // Gestion des sorties de terrains        for(int i = 0; i < characters.size(); i++){            if(characters.get(i).getCollider().exit(initMapLimit, mapLimit)){                characters.get(i).update(new Position(prevDisp[i].getX(), prevDisp[i].getY()));            }        }    }}