package bankAccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import bankAccount.Account;
import bankAccount.AccountingPeriod;
import bankAccount.CashFlow;
import junit.framework.Assert;
import util.DateHelper;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;


@SuppressWarnings("restriction")
public class SecondTry {
    static KieContainer kieContainer;
    StatelessKieSession sessionStateless = null;
    KieSession  sessionStatefull = null;

    @BeforeClass
    public static void beforeClass(){
        kieContainer=KnowledgeSessionHelper.createRuleBase();
    }

    @Before
    public void setUp() throws Exception{
        System.out.println("------------Before------------");
    }
    @After
    public void tearDown() throws Exception{
        System.out.println("------------After------------");
    }

    @Test
    public void testTwoFacts() throws Exception {
        sessionStatefull = KnowledgeSessionHelper
                   .getStatefulKnowledgeSessionWithCallback(kieContainer,"ksession-lesson2");
           OutputDisplay display = new OutputDisplay();
           sessionStatefull.setGlobal("showResults", display);
           Account a = new Account();
           a.setAccountno(1);
           a.setBalance(0);
           sessionStatefull.insert(a);
           CashFlow cash1 = new CashFlow();
           cash1.setAccountno(1);
           cash1.setAmount(1000);
           cash1.setMvtDate(DateHelper.getDate("2016-01-15"));
           cash1.setType(CashFlow.CREDIT);
           sessionStatefull.insert(cash1);
           CashFlow cash2 = new CashFlow();
           cash2.setAccountno(1);
           cash2.setAmount(250);
           cash2.setMvtDate(DateHelper.getDate("2016-02-15"));
           cash2.setType(CashFlow.DEBIT);
           sessionStatefull.insert(cash2);
           CashFlow cash3 = new CashFlow();
           cash3.setAccountno(1);
           cash3.setAmount(1000);
           cash3.setMvtDate(DateHelper.getDate("2016-04-15"));
           cash3.setType(CashFlow.CREDIT);
           sessionStatefull.insert(cash3);
           AccountingPeriod period = new AccountingPeriod();
           period.setStartDate(DateHelper.getDate("2016-01-01"));
           period.setEndDate(DateHelper.getDate("2016-03-31"));
           sessionStatefull.insert(period);
           sessionStatefull.fireAllRules();
    }
}