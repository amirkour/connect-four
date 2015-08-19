
package org.amirk.tests.games.connectfour.agents;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * when you want to add/remove tests to this test suite, edit THIS file,
 * don't mess with the ant tasks
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AgentUtilsTest.class,
    GameMoveSequenceComparatorTest.class,
    NPCConfigurableAgentTest.class
})
public class TestSuite{}
