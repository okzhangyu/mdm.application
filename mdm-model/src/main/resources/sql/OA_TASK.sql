Create Procedure "AVA_SP_OA_TASKS"(
 	in object_type nvarchar(20), 	-- SBO Object Type
	in transaction_type nchar(1),-- [A]dd, [U]pdate, [D]elete, [C]ancel, C[L]ose
	in num_of_cols_in_key integer,
	in list_of_key_cols_tab_del nvarchar(255),
	in list_of_cols_val_tab_del nvarchar(255) )
	LANGUAGE SQLSCRIPT
	As
	BEGIN
	Declare DocEntry int;
	Declare Flag int;
	Declare CompanyName nvarchar(30);
	Declare Objtype varchar(30);
	Declare Autokey int;
	AutoKey := 1;
	--select (select top 1 t1."CODE"    from CINF t0
	--inner join OA_MID_BASE_TEST.AVA_OA_COMPANY t1 on t0."CompnyName" = t1."NAME") as code into CompanyName from dummy

	select top 1 t1."CODE" into CompanyName   from CINF t0
	inner join OA_MID_BASE_TEST.AVA_OA_COMPANY t1 on t0."CompnyName" = t1."NAME";

	SELECT COUNT(*) INTO Flag FROM OA_MID_BASE_TEST."AVA_OA_TASK";
	IF (:FLAG > 0)
	THEN
		SELECT MAX(DocEntry) INTO AutoKey FROM OA_MID_BASE_TEST."AVA_OA_TASK";
	END IF;
	-------------------单据添加--------------------------------
	IF :transaction_type = N'A' THEN
		--科目
		IF :object_type = N'1' THEN
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "ObjType") + AutoKey,CompanyName,"AcctCode","ObjType",'A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM OACT Where "AcctCode" =  :list_of_cols_val_tab_del ;
		End IF;
		--项目
		IF :object_type = N'63' THEN
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "PrjCode") + AutoKey,CompanyName,"PrjCode",'63','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM OPRJ Where "PrjCode" =  :list_of_cols_val_tab_del ;
		End IF;
		--现金流
		IF :object_type = N'242' THEN
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "CFWId") + AutoKey,CompanyName,"CFWId",'242','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM OCFW Where "CFWId" =  :list_of_cols_val_tab_del ;
		End IF;
		--成本中心
		IF :object_type = N'61' THEN
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "PrcCode") + AutoKey,CompanyName,"PrcCode",'61','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM OPRC Where "PrcCode" =  :list_of_cols_val_tab_del ;
		End IF;
		-- 部门
		IF :object_type = N'119' THEN
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "Code") + AutoKey,CompanyName,"Code",'119','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM OUDP Where "Code" =  :list_of_cols_val_tab_del ;
		End IF;

		-- 人员
		IF :object_type = N'171' THEN
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "empID") + AutoKey,CompanyName,"empID",'171','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM OHEM Where "empID" =  :list_of_cols_val_tab_del ;
		End IF;

	END IF; -- INSERT

	---------------------单据更新----------------------------------------

	--更新单据
	IF :transaction_type = N'U'
	THEN
		--科目
		IF :object_type = N'1'
		THEN

				insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
				select ROW_NUMBER() Over(Order by "ObjType") + AutoKey,CompanyName,"AcctCode","ObjType",'U'
					,CURRENT_DATE,concat(hour(current_time),minute(current_time))
					FROM OACT Where "AcctCode" =  :list_of_cols_val_tab_del
					and not exists(select UniqueKey from  OA_MID_BASE_TEST."AVA_OA_TASK" where CompanyCode = CompanyName
																	and UniqueKey =  :list_of_cols_val_tab_del
																	and ObjectCode = '1'
																	and (IsSync = 'N' or IsSync = 'E'));
		END IF;

		--项目
		IF :object_type = N'63'
		THEN

				insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
				select ROW_NUMBER() Over(Order by "PrjCode") + AutoKey,CompanyName,"PrjCode",'63','U'
					,CURRENT_DATE,concat(hour(current_time),minute(current_time))
					FROM OPRJ Where "PrjCode" =  :list_of_cols_val_tab_del
					and not exists(select UniqueKey from  OA_MID_BASE_TEST."AVA_OA_TASK" where CompanyCode = CompanyName
																	and UniqueKey =  :list_of_cols_val_tab_del
																	and ObjectCode = '63'
																	and (IsSync = 'N' or IsSync = 'E'));

		END IF;
		--现金流
		IF :object_type = N'242'
		THEN

				insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
				select ROW_NUMBER() Over(Order by "CFWId") + AutoKey,CompanyName,"CFWId",'242','U'
					,CURRENT_DATE,concat(hour(current_time),minute(current_time))
					FROM OCFW Where "CFWId" =  :list_of_cols_val_tab_del
					and not exists(select UniqueKey from  OA_MID_BASE_TEST."AVA_OA_TASK" where CompanyCode = CompanyName
																	and UniqueKey =  :list_of_cols_val_tab_del
																	and ObjectCode = '242'
																	and (IsSync = 'N' or IsSync = 'E'));

		END IF;
		--成本中心
		IF :object_type = N'61'
		THEN

				insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
				select ROW_NUMBER() Over(Order by "PrcCode") + AutoKey,CompanyName,"PrcCode",'61','U'
					,CURRENT_DATE,concat(hour(current_time),minute(current_time))
					FROM OPRC Where "PrcCode" =  :list_of_cols_val_tab_del
					and not exists(select UniqueKey from  OA_MID_BASE_TEST."AVA_OA_TASK" where CompanyCode = CompanyName
																	and UniqueKey =  :list_of_cols_val_tab_del
																	and ObjectCode = '61'
																	and (IsSync = 'N' or IsSync = 'E'));

		END IF;
		--部门
		IF :object_type = N'119'
		THEN

				insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
				select ROW_NUMBER() Over(Order by "Code") + AutoKey,CompanyName,"Code",'119','U'
					,CURRENT_DATE,concat(hour(current_time),minute(current_time))
					FROM OUDP Where "Code" =  :list_of_cols_val_tab_del
					and not exists(select UniqueKey from  OA_MID_BASE_TEST."AVA_OA_TASK" where CompanyCode = CompanyName
																	and UniqueKey =  :list_of_cols_val_tab_del
																	and ObjectCode = '119'
																	and (IsSync = 'N' or IsSync = 'E'));

		END IF;
		--人员
		IF :object_type = N'171'
		THEN

				insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
				select ROW_NUMBER() Over(Order by "empID") + AutoKey,CompanyName,"empID",'171','U'
					,CURRENT_DATE,concat(hour(current_time),minute(current_time))
					FROM OHEM Where "empID" =  :list_of_cols_val_tab_del
					and not exists(select UniqueKey from  OA_MID_BASE_TEST."AVA_OA_TASK" where CompanyCode = CompanyName
																	and UniqueKey =  :list_of_cols_val_tab_del
																	and ObjectCode = '171'
																	and (IsSync = 'N' or IsSync = 'E'));

		END IF;
	END IF;
END;