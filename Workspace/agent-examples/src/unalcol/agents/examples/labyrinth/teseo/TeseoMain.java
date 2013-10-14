package unalcol.agents.examples.labyrinth.teseo;
import UNEquipoImplementations.teseo.SimpleTeseoAgentProgramClassExercise;
import unalcol.agents.Agent;

import unalcol.agents.examples.labyrinth.*;
import unalcol.agents.simulate.util.*;

public class TeseoMain {
  private static SimpleLanguage getLanguage(){
    return  new SimpleLanguage( new String[]{"front", "right", "back", "left", "exit"},
                                   new String[]{"no_op", "die", "advance", "rotate"}
                                   );
  }

  public static void main( String[] argv ){
    //InteractiveAgentProgram p = new InteractiveAgentProgram( getLanguage() );
    SimpleTeseoAgentProgramClassExercise p = new SimpleTeseoAgentProgramClassExercise();
    p.setLanguage(getLanguage());
    LabyrinthDrawer.DRAW_AREA_SIZE = 600;
    LabyrinthDrawer.CELL_SIZE = 40;
    Labyrinth.DEFAULT_SIZE = 15;
    Agent agent = new Agent( p );
//    Agent agent = new Agent( new RandomReflexTeseoAgentProgram( getLanguage() ) );
    TeseoMainFrame frame = new TeseoMainFrame( agent, getLanguage() );
    frame.setVisible(true); 
  }
}
