alter Procedure "AVA_SP_ADDON_TASKS"(
 	in object_type nvarchar(20), 	-- SBO Object Type
	in transaction_type nchar(1),-- [A]dd, [U]pdate, [D]elete, [C]ancel, C[L]ose
	in num_of_cols_in_key integer,
	in list_of_key_cols_tab_del nvarchar(255),
	in list_of_cols_val_tab_del nvarchar(255) )		
	LANGUAGE SQLSCRIPT
	As 
	BEGIN
	Declare DocEntry int;
	Declare OrderNo int;
	Declare Flag int;
	Declare CompanyDB nvarchar(30);
	Declare Objtype varchar(30);
	Declare Autokey int;
	AutoKey := 1;
	CompanyDB = 'SBO_KJS_ZDH';
	SELECT COUNT(*) INTO Flag FROM ADDON_MID_BASE."AVA_PM_TASK";
	IF (:FLAG > 0)
	THEN
		SELECT MAX(DocEntry) INTO AutoKey FROM ADDON_MID_BASE."AVA_PM_TASK";
	END IF;
	-------------------单据添加--------------------------------	
	IF :transaction_type = N'A' THEN
		--草稿
		-- BOM审批完成后 判断是新增的还是更新的，如果是更新的就插入进中间表
		IF :object_type = N'122' THEN
			
		End IF;
		
	END IF; -- INSERT
	
	---------------------单据更新----------------------------------------

	--更新单据
	IF :transaction_type = N'U'
	THEN
		If :object_type = N'122' THEN
			-- BOM审批
			insert into  "ADDON_MID_BASE"."AVA_PM_TASK"(DocEntry,CompanyCode,UniqueKey,ObjectCode,OpType,CreateDate,CreateTime)
			select ROW_NUMBER() Over(Order by t1."ObjType") + AutoKey,:CompanyDB,t1."U_BaseEntry",'AVA_PM_BOMNEW','A'
				,CURRENT_DATE,concat(hour(current_time),minute(current_time))
				from OWDD t0 inner join ODRF t1 on t0."DocEntry" = t1."DocEntry"  
						where t0."ObjType" = '112' and t0."Status" = 'Y' 
								and t1."ObjType" = '540000006' and t0."WddCode" = :list_of_cols_val_tab_del ;
			update "WOR1"  set "U_BaseEntry" = t0."DocEntry" from  "@AVA_PM_OVCO" t0,"WOR1" t1,"OWOR" t2,ODRF t3 , OWDD t4
                    where t3."DocEntry" = t4."DocEntry" and t4."ObjType" = '112' and  t4."Status" = 'Y' 
                    and t3."ObjType" = '540000006'
                    and t0."DocEntry" = t3."U_BaseEntry"
                    and t1."DocEntry" = t2."DocEntry"
                    and t0."DocEntry" = t4."U_LinkedExp"
                    and t2."ItemCode" = t0."U_ItemCode"
                    and t1."Project" = t0."U_Project"
                    and t1."U_WorkOrderNo" = t0."U_WorkId"
                    and t4."WddCode" = :list_of_cols_val_tab_del;
                    
			update "@AVA_PM_OVCO" t2 set "U_WddStatus" = 'A' from ODRF t0 inner join OWDD t1
                    on t0."DocEntry" = t1."DocEntry"  
                    where t1."ObjType" = '112' and  t1."Status" = 'Y' 
                    and t0."ObjType" = '540000006'
                    and t2."DocEntry" = t0."U_BaseEntry"
                    and t1."WddCode" = :list_of_cols_val_tab_del;
			update "@AVA_PM_OVCO" t2 set "U_WddStatus" = 'R' from ODRF t0 inner join OWDD t1
                    on t0."DocEntry" = t1."DocEntry"  
                    and t0."ObjType" = '540000006'
                    where t1."ObjType" = '112' and  t1."Status" = 'N' 
                    and t2."DocEntry" = t0."U_BaseEntry"
                    and t1."WddCode" = :list_of_cols_val_tab_del;
                    
            -- 项目预算审批
			
			update "@AVA_PM_OWTB" t2 set "U_WddStatus" = 'A' from ODRF t0 inner join OWDD t1
                    on t0."DocEntry" = t1."DocEntry"  
                    where t1."ObjType" = '112' and  t1."Status" = 'Y' 
                    and t0."ObjType" = '1250000001'
                    and t2."DocEntry" = t0."U_BaseEntry"
                    and t1."WddCode" = :list_of_cols_val_tab_del;
			update "@AVA_PM_OWTB" t2 set "U_WddStatus" = 'R' from ODRF t0 inner join OWDD t1
                    on t0."DocEntry" = t1."DocEntry"  
                    and t0."ObjType" = '1250000001'
                    where t1."ObjType" = '112' and  t1."Status" = 'N' 
                    and t2."DocEntry" = t0."U_BaseEntry"
                    and t1."WddCode" = :list_of_cols_val_tab_del;
             
			-- 工时汇报审批
			update "@AVA_PM_OACT" t2 set "U_WddStatus" = 'A' from ODRF t0 inner join OWDD t1
                    on t0."DocEntry" = t1."DocEntry"  
                    where t1."ObjType" = '112' and  t1."Status" = 'Y' 
                    and t0."ObjType" = '67'
                    and t2."DocEntry" = t0."U_BaseEntry"
                    and t1."WddCode" = :list_of_cols_val_tab_del;
			update "@AVA_PM_OACT" t2 set "U_WddStatus" = 'R' from ODRF t0 inner join OWDD t1
                    on t0."DocEntry" = t1."DocEntry"  
                    and t0."ObjType" = '67'
                    where t1."ObjType" = '112' and  t1."Status" = 'N' 
                    and t2."DocEntry" = t0."U_BaseEntry"
                    and t1."WddCode" = :list_of_cols_val_tab_del;
                    
            select (Select r0."U_BaseEntry"  from ODRF r0 inner join OWDD r1
 									on r0."DocEntry" = r1."DocEntry"  and r0."ObjType" = '67' 
 									where r1."ObjType" = '112' and  r1."Status" = 'Y' 
 									and r1."WddCode" = :list_of_cols_val_tab_del)  into OrderNo from dummy;
 			if( ifnull(OrderNo,0) > 0) then
 				call "AVA_SP_ADDON_UPDATE_TIME_BUDGET"(:OrderNo);
 			end if;	
		End If;
	END IF;   
END;
