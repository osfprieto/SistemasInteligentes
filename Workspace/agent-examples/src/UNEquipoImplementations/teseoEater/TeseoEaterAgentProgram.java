/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import unalcol.agents.simulate.util.SimpleLanguage;

/**
 *
 * @author Fredy Virguez
 */
public class TeseoEaterAgentProgram extends TeseoEaterAbstractAgentProgram {

    public TeseoEaterAgentProgram(SimpleLanguage language) {
        super(language);
    }

    @Override
    public String accion(boolean pf, boolean pd, boolean pa, boolean pi, boolean te, boolean co, boolean color, boolean forma, boolean tamano, boolean peso, boolean tipo, int energia) {

        if (!pd) {
            return OurActions.GO_RIGHT_STRING;
        }
        if (!pf) {
            return OurActions.GO_FRONT_STRING;
        }
        if (!pi) {
            return OurActions.GO_LEFT_STRING;
        }
        if (!pa) {
            return OurActions.GO_BACK_STRING;
        }
        return OurActions.DO_NOTHING_STRING;
    }
}
