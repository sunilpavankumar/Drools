package util;

import org.jbpm.workflow.instance.node.RuleSetNodeInstance;
import org.kie.api.KieServices;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

public class KnowledgeSessionHelper {

	public static KieContainer createRuleBase(){
		KieServices ks = KieServices.Factory.get();
		KieContainer kieContainer = ks.getKieClasspathContainer();
		return kieContainer;
	}
	
	public static StatelessKieSession getStatelessKnowledgeSession(KieContainer kieContainer,String sessionName){
		
		StatelessKieSession kSession = kieContainer.newStatelessKieSession(sessionName);
		
		return kSession;	
	}
	public static KieSession getStatefulKnowledgeSession(KieContainer kieContainer,String sessionName){
		
		KieSession kSession = kieContainer.newKieSession(sessionName);
		
		return kSession;	
	}
	
	public static KieSession getStatefulKnowledgeSessionWithCallback(
            KieContainer kieContainer, String sessionName) {
        KieSession session = getStatefulKnowledgeSession(kieContainer, sessionName);
        session.addEventListener(new RuleRuntimeEventListener() {
			
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
     
        session.addEventListener(new AgendaEventListener() {  public void matchCreated(MatchCreatedEvent event) {
            System.out.println("The rule "
                    + event.getMatch().getRule().getName()
                    + " can be fired in agenda");
        }
        public void matchCancelled(MatchCancelledEvent event) {
            System.out.println("The rule "
                    + event.getMatch().getRule().getName()
                    + " cannot b in agenda");
        }
        public void beforeMatchFired(BeforeMatchFiredEvent event) {
            System.out.println("The rule "
                    + event.getMatch().getRule().getName()
                    + " will be fired");
        }
        public void afterMatchFired(AfterMatchFiredEvent event) {
            System.out.println("The rule "
                    + event.getMatch().getRule().getName()
                    + " has be fired");
        }
        public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        }
        public void agendaGroupPushed(AgendaGroupPushedEvent event) {
        }
        public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        }
        public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        }
        public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        }
        public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        }});
        
        return session;
	}
	
	
	public static KieSession getStatefulKnowledgeSessionForJBPM(KieContainer kieContainer, String sessionName) {
         
		KieSession session = getStatefulKnowledgeSessionWithCallback(kieContainer,sessionName);
        session.addEventListener(new ProcessEventListener() {

              @Override
              public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {
                  // TODO Auto-generated method stub

              }

              @Override
              public void beforeProcessStarted(ProcessStartedEvent arg0) {
                  System.out.println("Process Name "+arg0.getProcessInstance().getProcessName()+" has been started");


              }

              @Override
              public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
                  // TODO Auto-generated method stub

              }

              @Override
              public void beforeNodeTriggered(ProcessNodeTriggeredEvent arg0) {
                  // TODO Auto-generated method stub

              }

              @Override
              public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {
                 if (arg0.getNodeInstance() instanceof RuleSetNodeInstance){
                      System.out.println("Node Name "+ arg0.getNodeInstance().getNodeName()+" has been left");        
                  }

              }

              @Override
              public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
                  // TODO Auto-generated method stub

              }

              @Override
              public void afterProcessStarted(ProcessStartedEvent arg0) {

              }

              @Override
              public void afterProcessCompleted(ProcessCompletedEvent arg0) {
                  System.out.println("Process Name "+arg0.getProcessInstance().getProcessName()+" has stopped");


              }

              @Override
              public void afterNodeTriggered(ProcessNodeTriggeredEvent arg0) {
                  if (arg0.getNodeInstance() instanceof RuleSetNodeInstance){
                      System.out.println("Node Name "+ arg0.getNodeInstance().getNodeName()+" has been entered");        
                  }
              }

              @Override
              public void afterNodeLeft(ProcessNodeLeftEvent arg0) {
               }
          });
        return session;
    }
	
}
