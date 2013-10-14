/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import java.util.Vector;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.simulate.util.SimpleLanguage;

/**
 *
 * @author osfprieto
 */
public class TeseoEaterAgentProgram implements AgentProgram{

    //"no_op", "die", "advance", "rotate", "eat"
    
  protected SimpleLanguage language;
  protected Vector<String> cmd = new Vector<String>();
  protected int ultimoNivelEnergia;
  
  protected String noOpLanguage;
  protected String dieLanguage;
  protected String advanceLanguage;
  protected String rotateLanguage;
  protected String eatLanguage;
  
  public TeseoEaterAgentProgram(SimpleLanguage language) {
      setLanguage(language);
      
  }

  public void setLanguage(  SimpleLanguage _language ){
    language = _language;
    noOpLanguage = language.getAction(0);
    dieLanguage = language.getAction(1);
    advanceLanguage = language.getAction(2);
    rotateLanguage = language.getAction(3);
    eatLanguage = language.getAction(4);
  }

  public void init(){
    cmd.clear();
  }

  public String accion(boolean pf, //Pared al frente
          boolean pd, //Pared derecha
          boolean pa, //Pared atrás
          boolean pi, //Pared izquierda
          boolean te, //Tesoro
          boolean co, //Resource - Comida
          boolean color, //Color comida
          boolean forma, //Forma comida
          boolean tamano, //Tamaño comida
          boolean peso, //Peso comida
          boolean tipo, //Tipo comida
          int energia){//Energía
      
      if(!pd)
          return OurActions.GO_RIGHT_STRING;
      if(!pf)
          return OurActions.GO_FRONT_STRING;
      if(!pi)
          return OurActions.GO_LEFT_STRING;
      if(!pa)
          return OurActions.GO_BACK_STRING;
      
      return OurActions.DO_NOTHING_STRING;
        
  }

  /**
   * execute
   *
   * @param perception Perception
   * @return Action[]
   */
  public Action compute(Percept p){
    if( cmd.isEmpty() ){
        
      //"front"0, "right"1, "back"2, "left"3, "treasure"4, "resource"5,
      //"resource-color"6, "resource-shape"7, "resource-size"8,
      //"resource-weight"9, "resource-type"10, "energy_level"11
        

      boolean PF = ( (Boolean) p.getAttribute(language.getPercept(0))).
          booleanValue();
      boolean PD = ( (Boolean) p.getAttribute(language.getPercept(1))).
          booleanValue();
      boolean PA = ( (Boolean) p.getAttribute(language.getPercept(2))).
          booleanValue();
      boolean PI = ( (Boolean) p.getAttribute(language.getPercept(3))).
          booleanValue();
      boolean TRE = ( (Boolean) p.getAttribute(language.getPercept(4))).
          booleanValue();
      boolean RES = ( (Boolean) p.getAttribute(language.getPercept(5))).
          booleanValue();
      boolean COL = ( (Boolean) p.getAttribute(language.getPercept(6))).
          booleanValue();
      boolean SHA = ( (Boolean) p.getAttribute(language.getPercept(7))).
          booleanValue();
      boolean SIZ = ( (Boolean) p.getAttribute(language.getPercept(8))).
          booleanValue();
      boolean WEI = ( (Boolean) p.getAttribute(language.getPercept(9))).
          booleanValue();
      boolean TYP = ( (Boolean) p.getAttribute(language.getPercept(10))).
          booleanValue();
      
      int EL = ((Integer) p.getAttribute(language.getPercept(11))).intValue();
      
      String accion = accion(PF, PD, PA, PI, TRE, RES, COL, SHA, SIZ, WEI, TYP, EL);
      
      if(accion.equals(OurActions.GO_BACK_STRING)){
          cmd.add(rotateLanguage);
          cmd.add(rotateLanguage);
          cmd.add(advanceLanguage);
      }
      else if(accion.equals(OurActions.GO_FRONT_STRING)){
          cmd.add(advanceLanguage);
      } 
      else if(accion.equals(OurActions.GO_LEFT_STRING)){
          cmd.add(rotateLanguage);
          cmd.add(rotateLanguage);
          cmd.add(rotateLanguage);
          cmd.add(advanceLanguage);
      }
      else if(accion.equals(OurActions.GO_RIGHT_STRING)){
          cmd.add(rotateLanguage);
          cmd.add(advanceLanguage);
      }
      else if(accion.equals(OurActions.DO_NOTHING_STRING)){
          cmd.add(dieLanguage);
      }
      else if(accion.equals(OurActions.EAT_STRING)){
          cmd.add(eatLanguage);
      }
      
    }
    String x = cmd.get(0);
    cmd.remove(0);
    return new Action(x);
  }

  /**
   * goalAchieved
   *
   * @param perception Perception
   * @return boolean
   */
  public boolean goalAchieved( Percept p ){
    //return false;
    return (((Boolean)p.getAttribute(language.getPercept(4))).booleanValue());
  }
    
}
