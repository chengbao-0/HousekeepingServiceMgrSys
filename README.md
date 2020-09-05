# HousekeepingServiceMgrSys
This is a simple project for housekeeping service companies to manage the employment information of members and housekeeping staff.

Developed by Eclipse(Java) + Mysql(sql)

For detailed database design, please restore by yourself according to sql backup records

"mysql-connector-java-5.1.0-bin.jar" is the JDBC driver jar package of the Mysql database used by the project

The homework requirements for Java elementary school coursework are as follows:
1. Able to realize the division of labor management of domestic staff (health care, maintenance, nanny, nursing, etc.).
2. And realize the work time management of housekeeping staff.
3. After logging in, members can select the corresponding housekeeping staff according to the division of labor.
4. The system can automatically screen out several suitable housekeeping personnel through the time period, and members can choose.
5. Pay and score after the service is completed.

On this basis:

Database backup and automatic backup
Simple statistics
Can not only score but also evaluate
The basic scene is:

　　 Members select housekeeping staff for employment according to the division of labor and time period

　　 After the successful employment, the housekeeping staff start to work (not the system is responsible for the actual work)

　　 The housekeeping staff will check in after finishing the work

　　 After punching in, the member starts to pay

　　 After the payment is completed, the member starts to score and evaluate, and the whole process ends.

In addition, the registration of members and housekeeping personnel is provided. The registration of housekeeping personnel needs to be reviewed by the administrator, and the new administrator account can only be added by the old administrator. The administrator can also modify the price of the corresponding service category, add service types, and so on.
