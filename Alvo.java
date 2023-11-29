package pt.iscte.poo.sokobanstarter;
import pt.iscte.poo.utils.Point2D;

public class Alvo extends GameElement implements Interaction{

    public boolean isOnRightPosition = false;

    public Alvo(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Alvo";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public void interactWith(GameElement ge) {
        if (ge instanceof Caixote && ge.getPosition().equals(this.getPosition())){
            isOnRightPosition = true;
		} else {
            isOnRightPosition = false;
        }
    }

    public boolean isOnRightPosition() {
        return this.isOnRightPosition;
    }


	// funcao que verifica se um caixote está em cima de um alvo
    GameEngine engine = GameEngine.getInstance();

	public boolean isMovableOnTarget(String target, String movable) {
		for (GameElement ge1 : engine.getGameElementsList() ) {
			if (ge1.getName().equals(target)) {
				for (GameElement ge2 : engine.getGameElementsList()) {
					if (ge2.getName().equals(movable) && ge2 instanceof Movable && ge1.getPosition().equals(((Movable)ge2).nextPosition(engine.getGui().keyPressed())) 
					&& engine.bobcat.nextPosition(engine.getGui().keyPressed()).equals(ge2.getPosition())) {
						return true;
					}
				}
			}
		}
		return false;
	}

    // funcao que verifica se um ponto tem algum gameElement em cima 
	public boolean isSomethingAbove(Point2D point , String name){
		for (GameElement ge : engine.getGameElementsList()) {
			if (ge.getPosition().equals(point) && ge.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

}