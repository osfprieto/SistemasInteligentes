/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import unalcol.agents.Action;

/**
 *
 * @author osfprieto
 */
public class TeseoEaterActions {
    
    public static final String GO_RIGHT_STRING = "GO_RIGHT";
    public static final String GO_LEFT_STRING = "GO_LEFT";
    public static final String GO_FRONT_STRING = "GO_FRONT";
    public static final String GO_BACK_STRING = "GO_BACK";
    public static final String DO_NOTHING_STRING = "DO_NOTHING";
    public static final String EAT_STRING = "EAT";
    public static final String DIE_STRING = "DIE";
    
    public static final Action GO_RIGHT = new Action(GO_RIGHT_STRING);
    public static final Action GO_LEFT = new Action(GO_LEFT_STRING);
    public static final Action GO_FRONT = new Action(GO_FRONT_STRING);
    public static final Action GO_BACK = new Action(GO_BACK_STRING);
    public static final Action DO_NOTHING = new Action(DO_NOTHING_STRING);
    public static final Action EAT = new Action(EAT_STRING);
    public static final Action DIE = new Action(DIE_STRING);
    
}
