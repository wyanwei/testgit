/*
 * Chsi
 * Created on 2021-02-20
 */
package com.perfat.boot.init.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Entity
@Table(name = "BOOT_SERVICE_START_LOG")
@Data
public class BootServiceStartLogData {
    @Id
    @GenericGenerator(name = "uu_id", strategy = "com.perfat.boot.util.StringRandomGenerator")
    @GeneratedValue(generator = "uu_id")
    private String id;
    private Calendar startTime;
}
