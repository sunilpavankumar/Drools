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
import org.kie.api.runtime.rule.FactHandle;

import bankAccount.Account;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

public class FirstTry2 {

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
		Account a = new Account();
		OutputDisplay d = new OutputDisplay();
		sessionStateful.setGlobal("showResult", d);
		sessionStateful.insert(a);
		sessionStateful.fireAllRules();
	}
	
	@Test
	public void testRuleOneFactwithFactAndUasageOfGlobalAndCallback(){
		
		sessionStateful = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");  
		sessionStateful.addEventListener(new RuleRuntimeEventListener() {
			
			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Object updated-"+arg0.getObject().toString());
				
			}
			
			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Object inserted-"+arg0.getObject().toString());
			}
			
			@Override
			public void objectDeleted(ObjectDeletedEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Object retracted-"+arg0.getOldObject().toString());
				
			}
		});
		
		Account a = new Account();
		a.setAccountno(10);
		FactHandle handlea =sessionStateful.insert(a);
		a.setBalance(12.0);
		sessionStateful.update(handlea, a);
		sessionStateful.delete(handlea);
		sessionStateful.fireAllRules();
		System.out.println("So you saw something");
		
		
				
		
	}

	
	
	
	
}
