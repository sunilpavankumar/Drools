package bankAccounts;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import bankAccount.CashFlow;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

public class FirstTry {

	StatelessKieSession sessionStateless=null;
	KieSession sessionStateful=null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass(){
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}
	
	@Test
	public void testFirstOne() {
		sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");
		
		OutputDisplay od = new OutputDisplay();
		sessionStateful.setGlobal("showResult", od);
		
		sessionStateful.addEventListener(new RuleRuntimeEventListener() {
			
			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("update called-"+arg0.getObject().toString());
			}
			
			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("insert called-"+arg0.getObject().toString());
			}
			
			@Override
			public void objectDeleted(ObjectDeletedEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("delete called-"+arg0.getOldObject().toString());
			}
		});
		
		CashFlow c = new CashFlow();
		c.setAmount(100);
		sessionStateful.insert(c);
		
		sessionStateful.fireAllRules();
	}

}
