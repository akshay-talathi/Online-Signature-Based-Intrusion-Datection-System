/*
SQLyog Community Edition- MySQL GUI v7.01 
MySQL - 4.1.10-nt : Database - signatureids
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`signatureids` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `signatureids`;

/*Table structure for table `customertable` */

DROP TABLE IF EXISTS `customertable`;

CREATE TABLE `customertable` (
  `CustID` int(11) NOT NULL auto_increment,
  `CustName` varchar(30) default NULL,
  `Address` varchar(50) default NULL,
  `MobNo` varchar(15) default NULL,
  `Email` varchar(40) default NULL,
  PRIMARY KEY  (`CustID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customertable` */

insert  into `customertable`(`CustID`,`CustName`,`Address`,`MobNo`,`Email`) values (1,'Rahul','Airoli','1234567890','rahul@gmail.com'),(2,'Hemant','Kalyan','9876543210','hemant@gmail.com');

/*Table structure for table `depttable` */

DROP TABLE IF EXISTS `depttable`;

CREATE TABLE `depttable` (
  `DeptID` int(11) NOT NULL default '0',
  `DeptName` varchar(30) default NULL,
  `Location` varchar(40) default NULL,
  PRIMARY KEY  (`DeptID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `depttable` */

insert  into `depttable`(`DeptID`,`DeptName`,`Location`) values (10,'Computer','Mumbai'),(20,'IT','Thane'),(30,'Support','Airoli');

/*Table structure for table `emptable` */

DROP TABLE IF EXISTS `emptable`;

CREATE TABLE `emptable` (
  `EmpID` int(11) NOT NULL auto_increment,
  `FirstName` varchar(30) default NULL,
  `LastName` varchar(30) default NULL,
  `Address` varchar(50) default NULL,
  `Salary` decimal(10,0) default NULL,
  `MobNo` varchar(15) default NULL,
  `Designation` varchar(40) default NULL,
  `DOB` datetime default NULL,
  `DeptID` int(11) default NULL,
  PRIMARY KEY  (`EmpID`),
  KEY `FK_emptable` (`DeptID`),
  CONSTRAINT `FK_emptable` FOREIGN KEY (`DeptID`) REFERENCES `depttable` (`DeptID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `emptable` */

insert  into `emptable`(`EmpID`,`FirstName`,`LastName`,`Address`,`Salary`,`MobNo`,`Designation`,`DOB`,`DeptID`) values (1,'Mujeeb','Siddique','Thane',16000,'9876543210','Java Developer','1990-06-13 00:00:00',10),(2,'Hemant','Mahajan','Airoli',15000,'1234567890','PHP Developer','1988-03-12 00:00:00',20),(3,'Sajid','Khan','Wagle Estate',12000,'9876543210','Clerk','1989-02-25 00:00:00',10),(4,'Zameer','Khan','Anand Nagar',18000,'1234567890','Accountance','1989-03-12 00:00:00',10);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `Uname` varchar(25) NOT NULL default '',
  `password` varchar(25) NOT NULL default '',
  `FullName` varchar(40) default NULL,
  PRIMARY KEY  (`Uname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`Uname`,`password`,`FullName`) values ('mujeeb','pass','Mujeeb Siddique');

/*Table structure for table `offlineaudit` */

DROP TABLE IF EXISTS `offlineaudit`;

CREATE TABLE `offlineaudit` (
  `ID` int(11) NOT NULL auto_increment,
  `Username` varchar(30) default NULL,
  `TransactionID` int(11) default NULL,
  `SequenceNo` int(11) default NULL,
  `CommandType` varchar(10) default NULL,
  `TargetObject` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `offlineaudit` */

insert  into `offlineaudit`(`ID`,`Username`,`TransactionID`,`SequenceNo`,`CommandType`,`TargetObject`) values (8,'mujeeb',1,1,'select','ProductTable'),(9,'mujeeb',1,2,'insert','ProductTable'),(10,'mujeeb',1,3,'select','CustomerTable');

/*Table structure for table `onlineaudit` */

DROP TABLE IF EXISTS `onlineaudit`;

CREATE TABLE `onlineaudit` (
  `ID` int(11) NOT NULL auto_increment,
  `Username` varchar(30) default NULL,
  `TransactionID` int(11) default NULL,
  `SequenceNo` int(11) default NULL,
  `CommandType` varchar(30) default NULL,
  `TargetObject` varchar(30) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `onlineaudit` */

/*Table structure for table `ordertable` */

DROP TABLE IF EXISTS `ordertable`;

CREATE TABLE `ordertable` (
  `OrderID` int(11) NOT NULL auto_increment,
  `ProductID` int(11) default NULL,
  `Quantity` int(11) default NULL,
  `CustID` int(11) default NULL,
  PRIMARY KEY  (`OrderID`),
  KEY `FK_ordertable` (`ProductID`),
  KEY `FK1_ordertable` (`CustID`),
  CONSTRAINT `FK1_ordertable` FOREIGN KEY (`CustID`) REFERENCES `customertable` (`CustID`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_ordertable` FOREIGN KEY (`ProductID`) REFERENCES `producttable` (`ProductID`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ordertable` */

insert  into `ordertable`(`OrderID`,`ProductID`,`Quantity`,`CustID`) values (1,4,3,1),(2,10,4,2),(3,3,2,1);

/*Table structure for table `originaldata` */

DROP TABLE IF EXISTS `originaldata`;

CREATE TABLE `originaldata` (
  `ID` int(11) NOT NULL auto_increment,
  `Username` varchar(30) default NULL,
  `TransactionID` int(11) default NULL,
  `Weight` varchar(40) default NULL,
  `Status` varchar(40) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `originaldata` */

/*Table structure for table `producttable` */

DROP TABLE IF EXISTS `producttable`;

CREATE TABLE `producttable` (
  `ProductID` int(11) NOT NULL auto_increment,
  `ProductName` varchar(40) default NULL,
  `ProductPrice` decimal(10,0) default NULL,
  PRIMARY KEY  (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `producttable` */

insert  into `producttable`(`ProductID`,`ProductName`,`ProductPrice`) values (1,'MOTHERBOARD',2500),(2,'RAM',1600),(3,'HARDDISK',3550),(4,'CDROM',1295),(5,'MONITOR',5700),(6,'mouse',350),(7,'UPS',2300),(8,'Keyboard',400),(9,'HandSet',550),(10,'Printer',5150),(11,'Wireless Mouse',950);

/*Table structure for table `trainingdata` */

DROP TABLE IF EXISTS `trainingdata`;

CREATE TABLE `trainingdata` (
  `ID` int(11) NOT NULL auto_increment,
  `UserName` varchar(30) default NULL,
  `TransactionID` int(11) default NULL,
  `Weight` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `trainingdata` */

insert  into `trainingdata`(`ID`,`UserName`,`TransactionID`,`Weight`) values (5,'mujeeb',1,'61,62,21');

/*Table structure for table `usertable` */

DROP TABLE IF EXISTS `usertable`;

CREATE TABLE `usertable` (
  `UserID` int(11) NOT NULL auto_increment,
  `UserName` varchar(30) NOT NULL default '',
  `Password` varchar(30) NOT NULL default '',
  PRIMARY KEY  (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usertable` */

insert  into `usertable`(`UserID`,`UserName`,`Password`) values (1,'Sajid','sajid'),(2,'abusaad','abusaad'),(3,'papa','papa'),(4,'firoz','firoz'),(5,'maqsood','maqsood'),(6,'khan','khan'),(7,'rauf','rauf'),(8,'zameer','zameer'),(9,'habib','habib');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
