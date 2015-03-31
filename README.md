# BeanTester
Tests normal bean methods such as accessors, mutators, equals(), hashcode(), etc.  If your organization has code coverage requirements, testing beans is some of the most monotonous work.  It's mindless and the less test code you need to write for this, the better.  BeanTester was written to get out of this monotonous work.  Predecessors to it have been copy/pasted/improved into almost a dozen projects over the years.  

Specifically, the following tests are performed:  
* All accessors (e.g. setXXX() methods) execute without exception    
* All mutators (e.g. getXXX() methods) execute without exception  
* accessors return the value assigned to them via their corresponding mutator (e.g. getXXX() returns the same value submitted with setXXX())  
* An instance equals() itself  
* hashcode() runs without exception    
* hashcode() is constant over time if no changes are made to it  
* clone() executes without exception  
* clone() equals the instance from which it was produced  

Additional detail in the 'Usage Instrcutions and Examples' section.


System Requirements
==================
* Java JDK 6.0 or above (it was compiled under JDK 7 using 1.6 as the target source).  
* Apache Commons Lang version 3.0 or above  
* Apache Commons Beanutils (tested with 1.9.2 but 1.8+ previous versions will likely work)  

Installation Instructions
==================
BeanTester is easy to install whether you use maven or not.

### Maven Users  
(forthcoming) -- Maven users can find dependency information [here](http://search.maven.org/#search%7Cga%7C1%7Corg.force66).

### Non-Maven Users  
Include the following jars in your class path:  
* Download the BeanTester jar from [Github](https://github.com/Force66/BeanTester/releases) and put it in your class path.  
* Insure Apache Commons Lang version 3.0 or above is in your class path. 
* Insure Apache Commons Beanutils version 1.8 or above is in your class path. 


Usage Instructions and Examples
==================

Basic usage example:  
```  
BeanTester beanTester = new BeanTester();
beanTester.testBean(PerformanceSummaryVO.class);  
```  

Exclude specific fields from the test if needed.
```  
BeanTester beanTester = new BeanTester();
beanTester.addExcludedField("firstOccuranceDt");
beanTester.addExcludedField("lastOccuranceDt");
beanTester.addExcludedField("totalNbrExceptions");
beanTester.testBean(PerformanceSummaryVO.class);  
```  

Add custom values for proerty-level testing:  
```  
BeanTester beanTester = new BeanTester();
beanTester.addTestValues(PerformanceSummaryVO.class, new Object[]{custom1, custom2});  
```  
