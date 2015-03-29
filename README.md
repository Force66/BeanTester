# BeanTester
*** Under Construction *** Tests normal bean methods such as accessors, mutators, equals(), hashcode(), etc.

In addition to accessors and mutators, toString(), hashcode(), equals(), and clone() are also tested.

Usage Examples
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

