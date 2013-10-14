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
            return TeseoEaterActions.GO_RIGHT_STRING;
        }
        if (!pf) {
            return TeseoEaterActions.GO_FRONT_STRING;
        }
        if (!pi) {
            return TeseoEaterActions.GO_LEFT_STRING;
        }
        if (!pa) {
            return TeseoEaterActions.GO_BACK_STRING;
        }
        return TeseoEaterActions.DO_NOTHING_STRING;
    }
}
