CREATE DATABASE lv_safety_verification;
use lv_safety_verification;

---------> Users Table <------------

CREATE TABLE users(    
			user_id INTEGER NOT NULL, 
			user_active BOOLEAN,
			creation_date DATETIME,
			email VARCHAR(255),
			first_name VARCHAR(255),
			last_name VARCHAR(255),
			password VARCHAR(255),
			user_role VARCHAR(255),
			user_exist BOOLEAN,
			user_name VARCHAR(255),
			user_type VARCHAR(255),
			updated_date DATETIME,
			one_time_password INTEGER
			PRIMARY KEY (user_id)
	);
	
--------> Country Table with insert queries <------------

CREATE TABLE country_table (
			COUNTRY_ID INTEGER NOT NULL, 
			NAME VARCHAR(255), 
			CODE VARCHAR(255), 
			CONSTRAINT PK_COUNTRY_ID PRIMARY KEY(COUNTRY_ID)
);

			INSERT INTO country_table VALUES (1, 'INDIA', 'IND');
			INSERT INTO country_table VALUES (2, 'UNITED STATES OF AMERICA', 'USA');
			INSERT INTO country_table VALUES (3, 'BANGLADESH', 'BGL');
			INSERT INTO country_table VALUES (4, 'SRILANKA', 'SRI');
			INSERT INTO country_table VALUES (5, 'UNITED KINGDOM', 'UK');
			INSERT INTO country_table VALUES (6, 'PAKISTAN', 'PAK');
			INSERT INTO country_table VALUES (7, 'AFGANISTAN', 'AFG');
			INSERT INTO country_table VALUES (8, 'MALDIVES', 'MDV');
			INSERT INTO country_table VALUES (9, 'UNITED ARAB EMIRATES', 'UAE');
			INSERT INTO country_table VALUES (10, 'CHINA', 'CHN');
			INSERT INTO country_table VALUES (11, 'SINGAPORE', 'SGP');
			INSERT INTO country_table VALUES (12, 'THAILAND', 'THL');
			INSERT INTO country_table VALUES (13, 'AUSTRALIA', 'AUS');
			INSERT INTO country_table VALUES (14, 'NEW ZEALAND', 'NZL');
			INSERT INTO country_table VALUES (15, 'JAPAN', 'JPN');
			INSERT INTO country_table VALUES (16, 'INDONESIA', 'INA');
			INSERT INTO country_table VALUES (17, 'MALAYSIA', 'MLY');
			INSERT INTO country_table VALUES (18, 'GERMANY', 'GNY');
			INSERT INTO country_table VALUES (19, 'FRANCE', 'FRN');
			INSERT INTO country_table VALUES (20, 'RUSSIA', 'RUS');
            INSERT INTO country_table VALUES (21, 'NEPAL', 'NPL');


--------> State Table with insert queries <------------

CREATE TABLE state_table (
			STATE_ID INT NOT NULL, 
			COUNTRY_ID INT, 
			NAME VARCHAR(255), 
			CODE VARCHAR(255),
			CONSTRAINT PK_STATE_ID PRIMARY KEY(STATE_ID),
			CONSTRAINT FK_COUNTRY_ID FOREIGN KEY (COUNTRY_ID) REFERENCES country_table(COUNTRY_ID) ON DELETE CASCADE
);

			INSERT INTO state_table VALUES (1,1,'Tamil Nadu','TN');
			INSERT INTO state_table VALUES (2,1,'Andhra Pradesh','AP');
			INSERT INTO state_table VALUES (3,1,'Telengana','TS');
			INSERT INTO state_table VALUES (4,1,'Karnataka','KA');
			INSERT INTO state_table VALUES (5,1,'Kerala','KL');
			INSERT INTO state_table VALUES (6,1,'Odisha','OR');
			INSERT INTO state_table VALUES (7,1,'Maharastra','MH');
			INSERT INTO state_table VALUES (8,1,'Madhya Pradesh','MP');
			INSERT INTO state_table VALUES (9,1,'Chattisgarh','CG');
			INSERT INTO state_table VALUES (10,1,'Jharkand','JD');
			INSERT INTO state_table VALUES (11,1,'Bihar','BH');
			INSERT INTO state_table VALUES (12,1,'Uttar Pradesh','UP');
			INSERT INTO state_table VALUES (13,1,'Gujarat','GU');
			INSERT INTO state_table VALUES (14,1,'Rajasthan','RJ');
			INSERT INTO state_table VALUES (15,1,'Punjab','PB');
			INSERT INTO state_table VALUES (16,1,'Haryana','HR');
			INSERT INTO state_table VALUES (17,1,'Uttarkhand','UT');
			INSERT INTO state_table VALUES (18,1,'Sikkim','SK');
			INSERT INTO state_table VALUES (19,1,'West Bengal','WB');
			INSERT INTO state_table VALUES (20,1,'Assam','AS');
			INSERT INTO state_table VALUES (21,1,'Arunachala Pradesh','AR');
			INSERT INTO state_table VALUES (22,1,'Nagaland','NG');
			INSERT INTO state_table VALUES (23,1,'Tripura','TP');
			INSERT INTO state_table VALUES (24,1,'Meghalaya','MG');
			INSERT INTO state_table VALUES (25,1,'Mizoram','MZ');
			INSERT INTO state_table VALUES (26,1,'Manipur','GU');
			INSERT INTO state_table VALUES (27,1,'Goa','GA');
			INSERT INTO STATE_TABLE VALUES (28,2,'Others','OT');
			INSERT INTO STATE_TABLE VALUES (29,21,'Others','OT');

--------> Applicationtypes Table with insert queries <------------

CREATE TABLE applicationtypes(
			ID INTEGER NOT NULL, 
			APPLICATION VARCHAR(255), 
			CODE VARCHAR(20), 
			PRIMARY KEY(ID),
			APPLICATION_NAME VARCHAR(10)

);

			 INSERT INTO APPLICATIONTYPES VALUES  (1,'Verification Of LV Systems (IEC 60364-6)', 'LV Systems','LV');
			 INSERT INTO APPLICATIONTYPES VALUES  (2,'Verification Of HV Systems (up to 33 kV) (IEC 61936-1)', 'HV Systems','HV');
			 INSERT INTO APPLICATIONTYPES VALUES  (3,'Lightning Protection Conformity Assessment, Risk Assessment, Inspection And Maintenance (IEC 62305-3 & 4)', 'Risk Assessment', 'RIA');
			 INSERT INTO APPLICATIONTYPES VALUES  (4,'EMC Assessment Of An Installation (IEC 61000-5-1)', 'EMC Assessment','EMC');
			 INSERT INTO APPLICATIONTYPES VALUES  (5,'Failure Analysis Of Electronic Systems', 'Failure Analysis','FAS');
			 INSERT INTO APPLICATIONTYPES VALUES  (6,'Conformity And Project Analysis', 'Conformity Project','CPA');
		     INSERT INTO APPLICATIONTYPES VALUES  (7,'Testing Inspection and Certification of Lightning protection system', 'LPS System', 'LPS');

--------> Site Table <------------

CREATE TABLE site_table (
		    SITE_ID INT AUTO_INCREMENT,
			SITE_CODE VARCHAR(255) NOT NULL,
		    USER_NAME VARCHAR(255),
			SITE VARCHAR(255) NOT NULL,
			COMPANY_NAME VARCHAR(255) NOT NULL,
			DEPARTMENT_NAME VARCHAR(255) NOT NULL,
			ADDRESSLINE_1 VARCHAR(255) NOT NULL,
			ADDRESSLINE_2 VARCHAR(255) NULL,
			LAND_MARK VARCHAR(255) NULL,
			CITY VARCHAR(255) NULL,
			STATE VARCHAR(255) NOT NULL,
			COUNTRY VARCHAR(255) NOT NULL,
			ZIP_CODE VARCHAR(255) NOT NULL,
			ALL_STEPS_COMPLETED VARCHAR(255),
			SITE_ASSIGNED_TO VARCHAR(255) NOT NULL,
			CREATED_BY VARCHAR(255) NOT NULL,
		    UPDATED_BY VARCHAR(255) NOT NULL,
		    CREATED_DATE datetime NOT NULL,    
		    UPDATED_DATE datetime NOT NULL,
			CONSTRAINT PK_SITE_ID PRIMARY KEY(SITE_ID),
);

CREATE TABLE site_person_table(
			 PERSON_ID INT AUTO_INCREMENT,
			 SITE_NAME VARCHAR(255),
			 SITE_ID INT,
			 IN_ACTIVE BIT,
			 PERSON_INCHARGE VARCHAR(255),
			 E_mail VARCHAR(255) NOT NULL,
			 DESIGNATION VARCHAR(255),
			 CONTACT_NO VARCHAR(255),
			 CONSTRAINT PK_PERSON_ID PRIMARY KEY(PERSON_ID),
			 CONSTRAINT FK_SITE_ID FOREIGN KEY (SITE_ID) REFERENCES SITE_TABLE(SITE_ID) ON DELETE CASCADE
 );


-----------> Registration <----------

CREATE TABLE register_table (

				REGISTER_ID INT AUTO_INCREMENT,
				
				NAME VARCHAR(225),
 				COMPANY_NAME VARCHAR(225),
				USER_NAME VARCHAR(225),
				PASSWORD VARCHAR(225),
				ROLE VARCHAR(225),
				CONTACT_NUMBER VARCHAR(225),
				APPLICATION_TYPE VARCHAR(225),
				DEPARTMENT VARCHAR(225),
				DESIGNATION VARCHAR(225),
				ADDRESS VARCHAR(500),
				DISTRICT VARCHAR(225),
				COUNTRY VARCHAR(225),
				PINCODE  VARCHAR(225),
				STATE  VARCHAR(225),
				PERMISSION  VARCHAR(225),
				PERMISSION_BY  VARCHAR(225),
				COMMENT  VARCHAR(500),
				OTP_SESSION_KEY VARCHAR(255),
				ASSIGNED_BY VARCHAR(255),
				NO_OF_LICENCE VARCHAR(255),

				SITE_NAME VARCHAR(255) NULL,

				CREATED_BY VARCHAR(255) NOT NULL,
				UPDATED_BY VARCHAR(255) NOT NULL,
				CREATED_DATE datetime NOT NULL,    
				UPDATED_DATE datetime NOT NULL,			

 				CONSTRAINT PK_REGISTER_ID PRIMARY KEY(REGISTER_ID)
                
);



-----------> LPS <----------

			CREATE TABLE BASIC_LPS_TABLE(
						BASIC_LPS_ID INT AUTO_INCREMENT,
						CLIENT_NAME VARCHAR(225),
						PROJECT_NAME VARCHAR(225),
						PMC_NAME VARCHAR(225),
						CONSULTANT_NAME VARCHAR(225),
						CONTRACTOR_NAME VARCHAR(225),
						DEALER_CONTRACTOR VARCHAR(225),
						ADDRESS VARCHAR(225),
						LOCATION VARCHAR(225),
						INSTALLATION_CONTRACTOR VARCHAR(225),
						INDUSTRY_TYPE VARCHAR(225),
						BUILDING_TYPE VARCHAR(225),
						BUILDING_LENGTH VARCHAR(225),
						BUILDING_WIDTH VARCHAR(225),
					    BUILDING_HEIGHT VARCHAR(225),
						LEVEL_OF_PROTECTION VARCHAR(225),
						SOIL_RESISTIVITY VARCHAR(225),
						USER_NAME VARCHAR(225),
						CREATED_BY VARCHAR(255),
					    CREATED_DATE datetime,
					    UPDATED_BY VARCHAR(255),
			            UPDATED_DATE datetime,
						CONSTRAINT PK_BASIC_LPS_ID PRIMARY KEY(BASIC_LPS_ID)
			);
			
			CREATE TABLE BASIC_LPS_DESCRIPTION_TABLE(
						BASIC_LPS_DESCRIPTION_ID INT AUTO_INCREMENT,
						BASIC_LPS_ID INT,
						APPROVED_COPYOF_DRAWING_OBSERVATION VARCHAR(225),
						APPROVED_COPYOF_DRAWING_REMARKS VARCHAR(225),
						ARCHITECT_NAMEINOBSERVATION VARCHAR(225),
						ARCHITECT_NAMEINREMARKS VARCHAR(225),
						DATE_OF_DESIGN_OBSERVATION VARCHAR(225),
						DATE_OF_DESIGN_REMARKS VARCHAR(225),
					    OBSERVATION_APPROVED_BY VARCHAR(225),
						REMARKS_APPROVED_BY VARCHAR(225),
						DATEOF_APPROVAL_OB VARCHAR(225),
						DATEOF_APPROVAL_REM VARCHAR(225),
						OBSERVATION_DRAWING_NUMBER VARCHAR(225),
						REMARKS_DRAWING_NUMBER VARCHAR(225),
						OBSERVATION_REVISION_NUMBER VARCHAR(225),
						REMARKS_REVISION_NUMBER VARCHAR(225),
						OBSERVATION_DEVIATION_CHECKING VARCHAR(225),
						REMARKS_DEVIATION_CHECKING VARCHAR(225),
						OBSERVATION_QUALITY_OF_INSTALLATION VARCHAR(225),
						REMARKS_QUALITY_OF_INSTALLATION VARCHAR(225),
						CONSTRAINT PK_BASIC_LPS_DESCRIPTION_ID PRIMARY KEY(BASIC_LPS_DESCRIPTION_ID),
						CONSTRAINT FK_BASIC_LPS_ID FOREIGN KEY (BASIC_LPS_ID ) REFERENCES BASIC_LPS_TABLE(BASIC_LPS_ID ) ON DELETE CASCADE
			);
			

			
			
			
			
	-----------> LPS AIR-TERMINALS <----------	
	
			
          CREATE TABLE LPSAIRDESCRIPTION_TABLE(
                  LPSAIRDESCRIPTION_ID INT AUTO_INCREMENT,
                  USER_NAME VARCHAR(225),
                  BASIC_LPS_ID INT,
                  CONNECTION_MADE_BRAZINGOBSERVATION VARCHAR(225),
                  CONNECTION_MADE_BRAZINGREMARKS VARCHAR(225),
                  ELECTRICAL_EQUIPMENT_PLACEDOBSERVATION VARCHAR(225),
                  ELECTRICAL_EQUIPMENT_PLACEDREMARKS VARCHAR(225),
                  COMBUSTABLE_PARTOBSERVATION VARCHAR(225),
                  COMBUSTABLE_PARTREMARKS VARCHAR(225),
                  TERMINATION_MESH_CONDUCTOROBSERVATION VARCHAR(225),
                  TERMINATION_MESH_CONDUCTORREMARKS VARCHAR(225),
                  BONDING_EQUIPOTENTIALOBSERVATION VARCHAR(225),
                  BONDING_EQUIPOTENTIALREMARKS VARCHAR(225),
                  CREATED_BY VARCHAR(255),
			      CREATED_DATE datetime,
				  UPDATED_BY VARCHAR(255),
			      UPDATED_DATE datetime,
                  CONSTRAINT PK_LPSAIRDESCRIPTION_ID PRIMARY KEY(LPSAIRDESCRIPTION_ID)

          );

          CREATE TABLE LPSVERTICALAIRTERMINAL_TABLE(
                  LPSVERTICALAIRTERMINAL_ID INT AUTO_INCREMENT,
                  LPSAIRDESCRIPTION_ID INT,
                  PHYSICAL_INSPECTIONOBSERVATION VARCHAR(225),
                  PHYSICAL_INSPECTIONREMARKS VARCHAR(225),
                  MATERIAL_OF_TERMINALOBSERVATION  VARCHAR(225),
                  MATERIAL_OF_TERMINALREMARKS  VARCHAR(225),
                  SIZE_OF_TERMINALOBSERVATION   VARCHAR(225),
                  SIZE_OF_TERMINALREMARKS   VARCHAR(225),
                  HEIGHT_OF_TERMINALOBSERVATION  VARCHAR(225),
                  HEIGHT_OF_TERMINALREMARKS   VARCHAR(225),
                  ANGLEPROTECTION_HEIGHTOBSERVATION VARCHAR(225),
                  ANGLEPROTECTION_HEIGHTREMARKS   VARCHAR(225),
                  INSTALLATION_PROTRUDESOBSERVATION  VARCHAR(225),
                  INSTALLATION_PROTRUDESREMARKS  VARCHAR(225),
                  SUPPORT_FLATSURFACEOBSERVATION  VARCHAR(225),
                  SUPPORT_FLATSURFACEREMARKS  VARCHAR(225),
                  HEIGHT_FLATSURAFACEOBSERVATION  VARCHAR(225),
                  HEIGHT_FLATSURAFACEREMARKS  VARCHAR(225),
                  VAT_TO_ROOFCONDUCTOROBSERVATION   VARCHAR(225),
                  VAT_TO_ROOFCONDUCTORREMARKS  VARCHAR(225),
                  TOTAL_NUMBEROBSERVATION  VARCHAR(225),
                  TOTAL_NUMBERREMARKS  VARCHAR(225),
                  INSP_NO_OBS VARCHAR(225),
                  INSP_NO_REM VARCHAR(225),
                  INSP_PASSED_NO_OBS VARCHAR(225),
                  INSP_PASSED_NO_REM VARCHAR(225),
                  INSP_FAILED_NO_OBS VARCHAR(225),
                  INSP_FAILED_NO_REM VARCHAR(225),
                  CONSTRAINT PK_LPSVERTICALAIRTERMINAL_ID  PRIMARY KEY(LPSVERTICALAIRTERMINAL_ID),
                  CONSTRAINT FK_LPSAIRDESCRIPTION_ID FOREIGN KEY(LPSAIRDESCRIPTION_ID) REFERENCES LPSAIRDESCRIPTION_TABLE(LPSAIRDESCRIPTION_ID) ON DELETE CASCADE
                   );

          CREATE TABLE AIR_MESHDESCRIPTION_TABLE(
                  MESHDESCRIPTION_ID INT AUTO_INCREMENT,
                  LPSAIRDESCRIPTION_ID INT,
                  SIZE_OF_CONDUCTOROBSERVATION VARCHAR(225),
                  SIZE_OF_CONDUCTORREMARKS VARCHAR(225),
                  MESH_SIGEOBSERVATION VARCHAR(225),
                  MESH_SIGEREMARKS VARCHAR(225),
                  MAXIMUM_DISTANCEOBSERVATION VARCHAR(225),
                  MAXIMUM_DISTANCEREMARKS VARCHAR(225),
                  MINIMUM_DISTANCEOBSERVATION VARCHAR(225),
                  MINIMUM_DISTANCEREMARKS VARCHAR(225),
                  HEIGHT_OFCONDUCTOR_FLATSURAFACEOBSERVATION  VARCHAR(225),
                  HEIGHT_OFCONDUCTOR_FLATSURAFACEREMARKS VARCHAR(225),
                  CONSTRAINT PK_MESHDESCRIPTION_ID   PRIMARY KEY(MESHDESCRIPTION_ID),
                  CONSTRAINT FK_MESHDESCRIPTION_LPSAIRDESCRIPTION_ID FOREIGN KEY(LPSAIRDESCRIPTION_ID) REFERENCES LPSAIRDESCRIPTION_TABLE(LPSAIRDESCRIPTION_ID) ON DELETE CASCADE
             );


          CREATE TABLE AIR_HOLDERS_DESCRIPTION_TABLE(
                  HOLDERS_DESCRIPTION_ID INT AUTO_INCREMENT,
                  LPSAIRDESCRIPTION_ID INT,
                  PHYSICAL_INSPECTION_OBSERVATION VARCHAR(225),
                  PHYSICAL_INSPECTION_REMARKS VARCHAR(225),
                  CONDUCTORHOLDER_FLATSURAFACE_OBSERVATION  VARCHAR(225),
                  CONDUCTORHOLDER_FLATSURAFACE_REMARKS VARCHAR(225),
                  CODUCTOR_HOLDER_OBSERVATION VARCHAR(225),
                  CODUCTOR_HOLDER_REMARKS VARCHAR(225),
                  HOLDER_TYPE_OBSERVATION VARCHAR(225),
                  HOLDER_TYPE_REMARKS VARCHAR(225),
                  MATERIAL_HOLDER_OBSERVATION VARCHAR(225),
                  MATERIAL_HOLDER_REMARKS VARCHAR(225),
                  TOTAL_HOLDERSNO_OBSERVATION VARCHAR(225),
                  TOTAL_HOLDERSNO_REMARKS VARCHAR(225),
                  TOTALPARPET_HOLDER_OBSERVATION VARCHAR(225),
                  TOTALPARPET_HOLDER_REMARKS VARCHAR(225),
                  HO_INSP_NO_OBS VARCHAR(225),
                  HO_INSP_NO_REM VARCHAR(225),
                  HO_INSP_PASSED_NO_OBS VARCHAR(225),
                  HO_INSP_PASSED_NO_REM VARCHAR(225),
                  HO_INSP_FAILED_NO_OBS VARCHAR(225),
                  HO_INSP_FAILED_NO_REM VARCHAR(225),
                  PH_INSP_NO_OBS VARCHAR(225),
                  PH_INSP_NO_REM VARCHAR(225),
                  PH_INSP_PASSED_NO_OBS VARCHAR(225),
                  PH_INSP_PASSED_NO_REM VARCHAR(225),
                  PH_INSP_FAILED_NO_OBS VARCHAR(225),
                  PH_INSP_FAILED_NO_REM VARCHAR(225),
                  CONSTRAINT PK_HOLDERS_DESCRIPTION_ID   PRIMARY KEY(HOLDERS_DESCRIPTION_ID ),
                 CONSTRAINT FK_HOLDERS_DESCRIPTION_LPSAIRDESCRIPTION_ID FOREIGN KEY(LPSAIRDESCRIPTION_ID) REFERENCES LPSAIRDESCRIPTION_TABLE(LPSAIRDESCRIPTION_ID) ON DELETE CASCADE
             );


          CREATE TABLE AIR_CLAMPS_TABLE(
                  CLAMPS_ID INT AUTO_INCREMENT,
                  LPSAIRDESCRIPTION_ID INT,
                  PHYSICAL_INSPECTION_OBSERVATION VARCHAR(225),
                  PHYSICAL_INSPECTION_REMARKS VARCHAR(225),
                  CONDUCTORCLAMPS_FLATSURAFACE_OBSERVATION VARCHAR(225),
                  CONDUCTORCLAMPS_FLATSURAFACE_REMARKS VARCHAR(225),
                  INTERCONNECTION_OF_CLAMPS_OBSERVATION VARCHAR(225),
                  INTERCONNECTION_OF_CLAMPS_REMARKS VARCHAR(225),
                  CLAMP_TYPE_OBSERVATION VARCHAR(225),
                  CLAMP_TYPE__REMARKS VARCHAR(225),
                  MATERIAL_OF_CLAMPS_OBSERVATION VARCHAR(225),
                  MATERIAL_OF_CLAMPS_REMARKS VARCHAR(225),
                  TOTAL_CLAMPSNO_OBSERVATION VARCHAR(225),
                  TOTAL_CLAMPSNO_REMARKS VARCHAR(225),
                  INSP_NO_OBS VARCHAR(225),
                  INSP_NO_REM VARCHAR(225),
                  INSP_PASSED_NO_OBS VARCHAR(225),
                  INSP_PASSED_NO_REM VARCHAR(225),
                  INSP_FAILED_NO_OBS VARCHAR(225),
                  INSP_FAILED_NO_REM VARCHAR(225),
                  CONSTRAINT PK_CLAMPS_ID PRIMARY KEY(CLAMPS_ID),
                  CONSTRAINT FK_CLAMPS_LPSAIRDESCRIPTION_ID FOREIGN KEY(LPSAIRDESCRIPTION_ID) REFERENCES LPSAIRDESCRIPTION_TABLE(LPSAIRDESCRIPTION_ID) ON DELETE CASCADE
                );


         CREATE TABLE AIR_EXPANSION_TABLE(
                  EXPANSION_ID INT AUTO_INCREMENT,
                  LPSAIRDESCRIPTION_ID INT,
                  PHYSICAL_INSPECTION_OBSERVATION VARCHAR(225),
                  PHYSICAL_INSPECTION_REMARKS VARCHAR(225),
                  STRIGHTCONNECTOR_PIECS_OBSERVATION  VARCHAR(225),
                  STRIGHTCONNECTOR_PIECS_REMARKS  VARCHAR(225),
                  MATERIAL_OF_EXPANSIONPIECE_OBSERVATION  VARCHAR(225),
                  MATERIAL_OF_EXPANSIONPIECE_REMARKS VARCHAR(225),
                  TOTALNO_EXPANSIONPIECE_OBSERVATION VARCHAR(225),
                  TOTALNO_EXPANSIONPIECE_REMARKS VARCHAR(225),
                  INSP_NO_OBS VARCHAR(225),
                  INSP_NO_REM VARCHAR(225),
                  INSP_PASSED_NO_OBS VARCHAR(225),
                  INSP_PASSED_NO_REM VARCHAR(225),
                  INSP_FAILED_NO_OBS VARCHAR(225),
                  INSP_FAILED_NO_REM VARCHAR(225),
                  CONSTRAINT PK_EXPANSION_ID PRIMARY KEY(EXPANSION_ID),
                  CONSTRAINT FK_EXPANSION_LPSAIRDESCRIPTION_ID FOREIGN KEY(LPSAIRDESCRIPTION_ID) REFERENCES LPSAIRDESCRIPTION_TABLE(LPSAIRDESCRIPTION_ID) ON DELETE CASCADE
                );



           CREATE TABLE AIR_CONNECTORS_TABLE(
                   CONNECTORS_ID INT AUTO_INCREMENT,
                   LPSAIRDESCRIPTION_ID INT,
                   PHYSICAL_INSPECTION_OBSERVATION VARCHAR(225),
                   PHYSICAL_INSPECTION_REMARKS VARCHAR(225),
                   CHECKCONNECTION_CONNECTORS_OBSERVATION VARCHAR(225),
                   CHECKCONNECTION_CONNECTORS_REMARKS VARCHAR(225),
                   MATERIAL_OF_CONNECTORS_OBSERVATION VARCHAR(225),
                   MATERIAL_OF_CONNECTORS_REMARKS VARCHAR(225),
                   STRIGHT_CONNECTORS_OBSERVATION VARCHAR(225),
                   STRIGHT_CONNECTORS_REMARKS VARCHAR(225),
                   T_CONNECTORS_OBSERVATION VARCHAR(225),
                   T_CONNECTORS_REMARKS VARCHAR(225),
                   L_CONNECTORS_OBSERVATION VARCHAR(225),
                   L_CONNECTORS_REMARKS VARCHAR(225),
                   TOTALNO_CONNECTORS_OBSERVATION VARCHAR(225),
                   TOTALNO_CONNECTORS_REMARKS VARCHAR(225),
                   INSP_NO_OBS VARCHAR(225),
                   INSP_NO_REM VARCHAR(225),
                   INSP_PASSED_NO_OBS VARCHAR(225),
                   INSP_PASSED_NO_REM VARCHAR(225),
                   INSP_FAILED_NO_OBS VARCHAR(225),
                   INSP_FAILED_NO_REM VARCHAR(225),
                   CONSTRAINT PK_CONNECTORS_ID PRIMARY KEY(CONNECTORS_ID),
                   CONSTRAINT FK_CONNECTORS_LPSAIRDESCRIPTION_ID FOREIGN KEY(LPSAIRDESCRIPTION_ID) REFERENCES LPSAIRDESCRIPTION_TABLE(LPSAIRDESCRIPTION_ID) ON DELETE CASCADE
                 );

			
                 
                 
        	-----------> LPS DOWN-CONDUCTOR <----------	         
                 
                 
               CREATE TABLE DOWNCONDUCTORDESCRIPTION_TABLE(
                     DOWNCONDUCTORDESCRIPTION_ID INT AUTO_INCREMENT,
                     BI_METALLICISSUE_OB VARCHAR(225),
                     BASIC_LPS_ID INT,
                     BI_METALLICISSUE_REM VARCHAR(225),
                     WARNINGNOTICE_GROUNDLEVEL_OB VARCHAR(225), 
                     WARNINGNOTICE_GROUNDLEVEL_REM VARCHAR(225),
                     NOPOWER_DOWNCONDUCTOR_OB VARCHAR(225),
                     NOPOWER_DOWNCONDUCTOR_REM VARCHAR(225),
                     CONNECTIONSMADE_BY_BRAZING_OB VARCHAR(225),
                     CONNECTIONSMADE_BY_BRAZING_REM VARCHAR(225),
                     CHEMICAL_SPRINKLER_OB VARCHAR(225),
                     CHEMICAL_SPRINKLER_REM VARCHAR(225),
                     COMBUSTIBLEMATERIAL_WALL_OB VARCHAR(225),
                     COMBUSTIBLEMATERIAL_WALL_REM VARCHAR(225),
                     
                     CREATED_BY VARCHAR(255),
			         CREATED_DATE datetime,
				     UPDATED_BY VARCHAR(255),
			         UPDATED_DATE datetime,
                     CONSTRAINT PK_DOWNCONDUCTORDESCRIPTION_ID   PRIMARY KEY(DOWNCONDUCTORDESCRIPTION_ID)
                      );
                      
                      
                CREATE TABLE DOWNCONDUCTOR_TABLE(
                      DOWNCONDUCTOR_ID INT AUTO_INCREMENT,
                      DOWNCONDUCTORDESCRIPTION_ID INT,
                      LOCATION_NO_OB VARCHAR(225),
                      LOCATION_NO_REM VARCHAR(225),
                      PHYSICAL_INSPECTION_OB VARCHAR(225),
                      PHYSICAL_INSPECTION_REM VARCHAR(225),
                      CONDUCTOR_MATERIAL_OB VARCHAR(225),
                      CONDUCTOR_MATERIAL_REM VARCHAR(225),
                      CONDUCTOR_SIZE_OB VARCHAR(225),
                      CONDUCTOR_SIZE_REM VARCHAR(225),
                      DOWNCONDUCTOR_EXPOSED_OB VARCHAR(225),
                      DOWNCONDUCTOR_EXPOSED_RE VARCHAR(225),
                      DOWNCONDUCTOR_LOCATION_OB VARCHAR(225),
                      DOWNCONDUCTOR_LOCATION_REM VARCHAR(225),
                      DOWNCONDUCTOR_GUTTERS_OB VARCHAR(225),
                      DOWNCONDUCTOR_GUTTERS_REM VARCHAR(225),
                      ENSURE_DOWNCONDUCTOR_OB VARCHAR(225),
                      ENSURE_DOWNCONDUCTOR_REM VARCHAR(225),
                      INSTALLATION_DOWNCONDUCTOR_OB VARCHAR(225),
                      INSTALLATION_DOWNCONDUCTOR_REM VARCHAR(225),
                      MAXIMUM_DOWNCONDUCTOR_OB VARCHAR(225),
                      MAXIMUM_DOWNCONDUCTOR_REM VARCHAR(225),
                      MINIMUM_DOWNCONDUCTOR_OB VARCHAR(225),
                      MINIMUM_DOWNCONDUCTOR_REM VARCHAR(225),
                      TOTALNO_DOWNCONDUCTOR_OB VARCHAR(225),
                      TOTALNO_DOWNCONDUCTOR_REM VARCHAR(225),
                      INSPECTED_NO_OB VARCHAR(225),
                      INSPECTED_NO_REM VARCHAR(225),
                      INSPECTIONSPASSED_NO_OB VARCHAR(225),
                      INSPECTIONSPASSED_NO_REM VARCHAR(225),
                      INSPECTIONFAILED_NO_OB VARCHAR(225),
                      INSPECTIONFAILED_NO_REM VARCHAR(225),
                      CONSTRAINT PK_DOWNCONDUCTOR_ID   PRIMARY KEY(DOWNCONDUCTOR_ID),
                      CONSTRAINT FK_DOWNCONDUCTOR_ID FOREIGN KEY(DOWNCONDUCTORDESCRIPTION_ID) REFERENCES DOWNCONDUCTORDESCRIPTION_TABLE(DOWNCONDUCTORDESCRIPTION_ID) ON DELETE CASCADE
                    );
                    
                    
                CREATE TABLE BRIDGINGDESCRIPTION_TABLE(
                     BRIDGINGDESCRIPTION_ID INT AUTO_INCREMENT,
                     DOWNCONDUCTORDESCRIPTION_ID INT,
                     BRIDGING_CABLES_OB VARCHAR(225),
                     BRIDGING_CABLES_REM VARCHAR(225),
                     ENSURE_BRIDGINGCABLE_OB VARCHAR(225),
                     ENSURE_BRIDGINGCABLE_REM VARCHAR(225),
                     ALUMINIUMCONDUCTOR_SIDEWALL_OB VARCHAR(225),
                     ALUMINIUMCONDUCTOR_SIDEWALL_REM VARCHAR(225),
                     BRIDGINGCABLE_CONNECTION_OB VARCHAR(225),
                     BRIDGINGCABLE_CONNECTION_REM VARCHAR(225),
                     TOTALNO_BRIDGINGCABLE_OB VARCHAR(225),
                     TOTALNO_BRIDGINGCABLE_REM VARCHAR(225),
                     INSPECTED_NO_OB VARCHAR(225),
                     INSPECTED_NO_REM VARCHAR(225),
                     INSPECTIONSPASSED_NO_OB VARCHAR(225),
                     INSPECTIONSPASSED_NO_REM VARCHAR(225),
                     INSPECTIONFAILED_NO_OB VARCHAR(225),
                     INSPECTIONFAILED_NO_REM VARCHAR(225),

                     CONSTRAINT PK_BRIDGINGDESCRIPTION_ID    PRIMARY KEY(BRIDGINGDESCRIPTION_ID ),
                     CONSTRAINT FK_DOWNCONDUCTORDESCRIPTION_ID FOREIGN KEY(DOWNCONDUCTORDESCRIPTION_ID) REFERENCES DOWNCONDUCTORDESCRIPTION_TABLE(DOWNCONDUCTORDESCRIPTION_ID) ON DELETE CASCADE
                      );
                CREATE TABLE HOLDERS_TABLE(
                     HOLDERS_ID INT AUTO_INCREMENT,
                     DOWNCONDUCTORDESCRIPTION_ID INT,
                     PHYSICAL_INSPECTION_OB  VARCHAR(225),
                     PHYSICAL_INSPECTION_REM  VARCHAR(225),
                     CONDUCTORHOLDER_FLATSURFACE_OB VARCHAR(225),
                     CONDUCTORHOLDER_FLATSURFACE_REM VARCHAR(225),
                     CONDUCTOR_HOLDED_OB VARCHAR(225),
                     CONDUCTOR_HOLDED_REM VARCHAR(225),
                     MATERIAL_HOLDER_OB VARCHAR(225),
                     MATERIAL_HOLDER_REM VARCHAR(225),
                     TOTALNO_HOLDERS_OB VARCHAR(225),
                     TOTALNO_HOLDERS_REM VARCHAR(225),
                     INSPECTED_NO_OB VARCHAR(225),
                     INSPECTED_NO_REM VARCHAR(225),
                     INSPECTIONSPASSED_NO_OB VARCHAR(225),
                     INSPECTIONSPASSED_NO_REM VARCHAR(225),
                     INSPECTIONFAILED_NO_OB VARCHAR(225),
                     INSPECTIONFAILED_NO_REM VARCHAR(225),
                     CONSTRAINT PK_HOLDERS_ID PRIMARY KEY(HOLDERS_ID),
                     CONSTRAINT FK_HOLDERS_DOWNCONDUCTORDESCRIPTION_ID FOREIGN KEY(DOWNCONDUCTORDESCRIPTION_ID) REFERENCES DOWNCONDUCTORDESCRIPTION_TABLE(DOWNCONDUCTORDESCRIPTION_ID) ON DELETE CASCADE

                       );
                       
                       
                  CREATE TABLE CONNECTORS_TABLE(
                     CONNECTORS_ID INT AUTO_INCREMENT,
                     DOWNCONDUCTORDESCRIPTION_ID INT,
                     PHYSICAL_INSPECTION_OB VARCHAR(225),
                     PHYSICAL_INSPECTION_REM VARCHAR(225),
                     STRAIGHT_CONNECTORCHACK_OB VARCHAR(225),
                     STRAIGHT_CONNECTORCHACK_REM VARCHAR(225),
                     MATERIAL_CONNECTOR_OB VARCHAR(225),
                     MATERIAL_CONNECTOR_REM VARCHAR(225),
                     MAXCONNECTORS_DOWNCONDUCTORS_OB VARCHAR(225),
                     MAXCONNECTORS_DOWNCONDUCTORS_REM VARCHAR(225),
                     TOTALNO_CONNECTORS_OB VARCHAR(225),
                     TOTALNO_CONNECTORS_REM VARCHAR(225),
                     INSPECTED_NO_OB VARCHAR(225),
                     INSPECTED_NO_REM VARCHAR(225),
                     INSPECTIONSPASSED_NO_OB VARCHAR(225),
                     INSPECTIONSPASSED_NO_REM VARCHAR(225),
                     INSPECTIONFAILED_NO_OB VARCHAR(225),
                     INSPECTIONFAILED_NO_REM VARCHAR(225),
                     CONSTRAINT PK_CONNECTORS_ID    PRIMARY KEY(CONNECTORS_ID),
                     CONSTRAINT FK_CONNECTORS_DOWNCONDUCTORDESCRIPTION_ID FOREIGN KEY(DOWNCONDUCTORDESCRIPTION_ID) REFERENCES DOWNCONDUCTORDESCRIPTION_TABLE(DOWNCONDUCTORDESCRIPTION_ID) ON DELETE CASCADE
                  );     
                       
                 CREATE TABLE LIGHTNINGCOUNTERS_TABLE(
                     LIGHTNINGCOUNTERS_ID INT AUTO_INCREMENT,
                     DOWNCONDUCTORDESCRIPTION_ID INT,
                     THREADHOLD_CURRENT_OB VARCHAR(225),
                     THREADHOLD_CURRENT_REM VARCHAR(225),
                     MAXIMUMWITHSTAND_CURRENT_OB VARCHAR(225),
                     MAXIMUMWITHSTAND_CURRENT_REM VARCHAR(225),
                     COUNTS_OB VARCHAR(225),
                     COUNTS_REM VARCHAR(225),
                     BATTERY_LIFETIME_OB VARCHAR(225),
                     BATTERY_LIFETIME_REM VARCHAR(225),
                     PROPERCONNECTION_LIGHTINGCOUNTER_OB VARCHAR(225),
                     PROPERCONNECTION_LIGHTINGCOUNTER_REM VARCHAR(225),
                     LIGHTINGCOUNTER_PLACED_OB VARCHAR(225),
                     LIGHTINGCOUNTER_PLACED_REM VARCHAR(225),
                     CONDITION_OF_LIGHTINGCOUNTER_OB VARCHAR(225),
                     CONDITION_OF_LIGHTINGCOUNTER_REM VARCHAR(225),
                     TOTALNO_LIGHTNINGCOUNTER_OB VARCHAR(225),
                     TOTALNO_LIGHTNINGCOUNTER_REM VARCHAR(225),
                     INSPECTED_NO_OB VARCHAR(225),
                     INSPECTED_NO_REM VARCHAR(225),
                     INSPECTIONSPASSED_NO_OB VARCHAR(225),
                     INSPECTIONSPASSED_NO_REM VARCHAR(225),
                     INSPECTIONFAILED_NO_OB VARCHAR(225),
                     INSPECTIONFAILED_NO_REM VARCHAR(225),
                     CONSTRAINT PK_LIGHTNINGCOUNTERS_ID PRIMARY KEY(LIGHTNINGCOUNTERS_ID ),
                     CONSTRAINT FK_LIGHTNINGCOUNTERS_DOWNCONDUCTORDESCRIPTION_ID FOREIGN KEY(DOWNCONDUCTORDESCRIPTION_ID) REFERENCES DOWNCONDUCTORDESCRIPTION_TABLE(DOWNCONDUCTORDESCRIPTION_ID) ON DELETE CASCADE
                     );
                     
                     
                   CREATE TABLE TESTJOINTS_TABLE(
                     TESTJOINT_ID INT AUTO_INCREMENT,
                     DOWNCONDUCTORDESCRIPTION_ID INT,
                     TESTJOINT_TYPE_OB  VARCHAR(225), 
                     TESTJOINT_TYPE_REM  VARCHAR(225), 
                     MATERIAL_TESTJOINT_OB VARCHAR(225),
                     MATERIAL_TESTJOINT_REM  VARCHAR(225),
                     ACCESSIBILITY_OF_TESTJOINT_OB  VARCHAR(225),
                     ACCESSIBILITY_OF_TESTJOINT_REM  VARCHAR(225),
                     NONMETALICCASING_PROTECTION_OB  VARCHAR(225),
                     NONMETALICCASING_PROTECTION_REM  VARCHAR(225),
                     TESTJOINTPLACED_GROUNTLEVEL_OB  VARCHAR(225),
                     TESTJOINTPLACED_GROUNTLEVEL_REM  VARCHAR(225),
                     BIMETALLICISSUE_CHECK_OB  VARCHAR(225),
                     BIMETALLICISSUE_CHECK_REM  VARCHAR(225),
                     TOTALNO_OF_TESTJOINT_OB  VARCHAR(225),
                     TOTALNO_OF_TESTJOINT_REM  VARCHAR(225),
                     INSPECTED_NO_OB VARCHAR(225),
                     INSPECTED_NO_REM VARCHAR(225),
                     INSPECTIONSPASSED_NO_OB VARCHAR(225),
                     INSPECTIONSPASSED_NO_REM VARCHAR(225),
                     INSPECTIONFAILED_NO_OB VARCHAR(225),
                     INSPECTIONFAILED_NO_REM VARCHAR(225),
                     CONSTRAINT PK_TESTJOINT_ID  PRIMARY KEY(TESTJOINT_ID),
                     CONSTRAINT FK_TESTJOINTS_DOWNCONDUCTORDESCRIPTION_ID FOREIGN KEY(DOWNCONDUCTORDESCRIPTION_ID) REFERENCES DOWNCONDUCTORDESCRIPTION_TABLE(DOWNCONDUCTORDESCRIPTION_ID) ON DELETE CASCADE
                    );
