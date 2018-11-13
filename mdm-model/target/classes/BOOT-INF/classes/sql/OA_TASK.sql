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

	select top 1 t1."CODE" into CompanyName  from CINF t0
	inner join OA_MID_BASE_TEST.AVA_OA_COMPANY t1 on t0."CompnyName" = t1."NAME";


	SELECT COUNT(*) INTO Flag FROM OA_MID_BASE_TEST."AVA_OA_TASK";
	IF (:FLAG > 0)
	THEN
		SELECT MAX(DocEntry) INTO AutoKey FROM OA_MID_BASE_TEST."AVA_OA_TASK";
	END IF;
	-------------------单据添加--------------------------------
	IF :transaction_type = N'A' THEN
	--员工主数据
	IF  :object_type=N'171' then
		-- 激活状态为Y的表示更新
		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "empID") + AutoKey,CompanyName,"empID",'171','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM OHEM Where "empID" =  :list_of_cols_val_tab_del and ifnull("Active",'N') = 'Y';
	End IF;
	IF  :object_type=N'119' then
		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "Code") + AutoKey,CompanyName,"Code",'119','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM OUDP Where "Code" =  :list_of_cols_val_tab_del ;

				END IF;
	--销售订单
	IF  :object_type=N'17' then

		-- 合同号、工单号通过项目接口推送至OA
		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "U_HTH") + AutoKey,CompanyName,"U_HTH",'63','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM ORDR Where "DocEntry" =  :list_of_cols_val_tab_del ;
				SELECT MAX(DocEntry) INTO AutoKey FROM OA_MID_BASE_TEST."AVA_OA_TASK";
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "U_GDH") + AutoKey,CompanyName,"U_GDH",'63','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM ORDR T0 INNER JOIN RDR1 T1 ON T0."DocEntry"=T1."DocEntry" Where T0."DocEntry" =  :list_of_cols_val_tab_del ;

		end if;
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
		IF :object_type = N'540000042' THEN
			insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "PrcCode") + AutoKey,CompanyName,"PrcCode",'540000042','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				FROM OPRC Where "PrcCode" =  :list_of_cols_val_tab_del ;
		End IF;

	END IF; -- INSERT

	---------------------单据更新----------------------------------------

	--更新单据
	IF :transaction_type = N'U'
	THEN
		-- 人员更新
		IF  :object_type=N'171' then
		-- 激活状态为Y的表示更新
		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "empID") + AutoKey,CompanyName,"empID",'171','U'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM OHEM Where "empID" =  :list_of_cols_val_tab_del and ifnull("Active",'N') = 'Y';

		-- 激活状态为N的表示删除
		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "empID") + AutoKey,CompanyName,"empID",'171','D'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM OHEM Where "empID" =  :list_of_cols_val_tab_del and ifnull("Active",'N') = 'N';
		END IF;

		--部门更新
		IF  :object_type=N'119' then
		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by "Code") + AutoKey,CompanyName,"Code",'119','U'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM OUDP Where "Code" =  :list_of_cols_val_tab_del ;

		END IF;
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
		IF :object_type = N'540000042'
		THEN

				insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
				select ROW_NUMBER() Over(Order by "PrcCode") + AutoKey,CompanyName,"PrcCode",'540000042','U'
					,CURRENT_DATE,concat(hour(current_time),minute(current_time))
					FROM OPRC Where "PrcCode" =  :list_of_cols_val_tab_del
					and not exists(select UniqueKey from  OA_MID_BASE_TEST."AVA_OA_TASK" where CompanyCode = CompanyName
																	and UniqueKey =  :list_of_cols_val_tab_del
																	and ObjectCode = '540000042'
																	and (IsSync = 'N' or IsSync = 'E'));

		END IF;
	END IF;
	--删除单据
	IF :transaction_type = N'D'
	THEN

		--移除员工
		IF  :object_type=N'171' then
		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select 1 + AutoKey,CompanyName,:list_of_cols_val_tab_del,'171','D'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM dummy;
		END IF;
		--移除部门
		IF  :object_type=N'119' then

		insert into  OA_MID_BASE_TEST."AVA_OA_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select 1 + AutoKey,CompanyName, :list_of_cols_val_tab_del,'119','D'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
			FROM dummy;
		END IF;
	END IF;
END;