package persistence.pere;

import java.util.Properties;
import org.junit.Ignore;
import inserter.Inserter;
import junit.framework.TestCase;
import persistence.manager.CfgManagerSolo;

@Ignore
public class TU_Pere extends TestCase {
	private Inserter inserter = null;
	protected Properties testCfg = null;


	public void setUp() throws Exception {
		System.setProperty(CfgManagerSolo.OTHER_CFG_FILE, "files/testcfg.ini");
		testCfg = CfgManagerSolo.getInstance().getCfg();
		inserter = new Inserter(testCfg);
		inserter.insert("create.sql");
	}
	public void tearDown() throws Exception {
		inserter.insert("drop.sql");
	}
	public Inserter getInserter() {
		return inserter;
	}
}
