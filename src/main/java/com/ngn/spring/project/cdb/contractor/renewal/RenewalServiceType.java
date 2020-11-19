package com.ngn.spring.project.cdb.contractor.renewal;

/**
 * ====================================================================
 * Created by Nima Yoezer on 3/14/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class RenewalServiceType {
    private Integer incorporation;
    private Integer changeOfFirmName;
    private Integer changeOfLocation;
    private Integer changeOfOwner;
    private Integer upgradeDowngrade;
    private Integer updateHR;
    private Integer updateEq;

    public Integer getIncorporation() {
        return incorporation;
    }

    public void setIncorporation(Integer incorporation) {
        this.incorporation = incorporation;
    }

    public Integer getChangeOfFirmName() {
        return changeOfFirmName;
    }

    public void setChangeOfFirmName(Integer changeOfFirmName) {
        this.changeOfFirmName = changeOfFirmName;
    }

    public Integer getChangeOfLocation() {
        return changeOfLocation;
    }

    public void setChangeOfLocation(Integer changeOfLocation) {
        this.changeOfLocation = changeOfLocation;
    }

    public Integer getChangeOfOwner() {
        return changeOfOwner;
    }

    public void setChangeOfOwner(Integer changeOfOwner) {
        this.changeOfOwner = changeOfOwner;
    }

    public Integer getUpgradeDowngrade() {
        return upgradeDowngrade;
    }

    public void setUpgradeDowngrade(Integer upgradeDowngrade) {
        this.upgradeDowngrade = upgradeDowngrade;
    }

    public Integer getUpdateHR() {
        return updateHR;
    }

    public void setUpdateHR(Integer updateHR) {
        this.updateHR = updateHR;
    }

    public Integer getUpdateEq() {
        return updateEq;
    }

    public void setUpdateEq(Integer updateEq) {
        this.updateEq = updateEq;
    }
}
