package bankAccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import bankAccount.Account;
import bankAccount.AccountingPeriod;
import bankAccount.CashFlow;
import bankAccount.Customer;
import bankAccount.PrivateAccount;
import junit.framework.Assert;
import service.CustomerService;
import util.DateHelper;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;


@SuppressWarnings("restriction")
public class ThirdTry {
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
    public void testInConstrait() throws Exception {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSessionWithCallback(kieContainer, "ksession-lesson3");
        OutputDisplay display = new OutputDisplay();
        sessionStatefull.setGlobal("showResult", display);
        /*CashFlow cashFlow = new CashFlow();
        cashFlow.setType(CashFlow.CREDIT);
        sessionStatefull.insert(cashFlow);
        sessionStatefull.fireAllRules();*/
        
       /* Customer customer = new Customer();
        customer.setName("Heron");
        customer.setSurname("Nicolas");
        PrivateAccount pAccount = new PrivateAccount();
        pAccount.setOwner(customer);
        sessionStatefull.insert(pAccount);
        sessionStatefull.fireAllRules();*/
        
       /* Customer customer = new Customer();

        customer.setCountry("GB");
        sessionStatefull.insert(customer);
        PrivateAccount pAccount = new PrivateAccount();
        pAccount.setOwner(customer);
        sessionStatefull.insert(pAccount);*/
        
        /*sessionStatefull.insert(new Account());*/
        
       /* Account a = new Account();
        a.setAccountno(1);
        a.setBalance(0);
        
        
        sessionStatefull.insert(a);
        CashFlow cash1 = new CashFlow();
        cash1.setAccountno(1);


        sessionStatefull.insert(cash1);
        CashFlow cash2 = new CashFlow();
        cash2.setAccountno(1);

        sessionStatefull.insert(cash2);
        Account a2 = new Account();
        a2.setAccountno(2);
        a2.setBalance(0);
        sessionStatefull.insert(a2);
        CashFlow cash3 = new CashFlow();
        cash3.setAccountno(2);
        sessionStatefull.insert(cash3);
*/
        
      /*  sessionStatefull.setGlobal("serviceCustomer", new CustomerService());
        Customer c = new Customer("Héron", "Nicolas", "A");
        sessionStatefull.insert(c);*/
        
        
    /*    Account a = new Account();
        a.setAccountno(1);
        a.setBalance(0);
        sessionStatefull.insert(a);
        sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-01-15"), 1000, CashFlow.CREDIT, 1));
        sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-02-15"), 500, CashFlow.DEBIT, 1));
        sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-04-15"), 1000, CashFlow.CREDIT, 1));
        sessionStatefull
                .insert(new AccountingPeriod(DateHelper.getDate("2010-01-01"), DateHelper.getDate("2010-31-31")));
*/
        
       Account a = new Account();
        a.setAccountno(1);
        a.setBalance(0);
        sessionStatefull.insert(a);

        FactHandle fa = sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-01-15"), 1000, CashFlow.CREDIT, 1));
        sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-02-15"),500,CashFlow.DEBIT,1));
        sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-04-15"),1000,CashFlow.CREDIT,1));
        sessionStatefull.insert(new AccountingPeriod(DateHelper.getDate("2010-01-01"),DateHelper.getDate("2010-12-31")));
        sessionStatefull.fireAllRules();
        sessionStatefull.delete(fa);
        
        sessionStatefull.fireAllRules();

    }    
}