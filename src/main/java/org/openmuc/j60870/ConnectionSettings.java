/*
 * Copyright 2014-2022 Fraunhofer ISE
 *
 * This file is part of j60870.
 * For more information visit http://www.openmuc.org
 *
 * j60870 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * j60870 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with j60870.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.j60870;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ConnectionSettings {
    private static final ExecutorService threadPool;
    private static volatile int numOpenConnections;

    static {
        threadPool = Executors.newCachedThreadPool();
        numOpenConnections = 0;
    }

    private int messageFragmentTimeout;

    private int cotFieldLength;
    private int commonAddressFieldLength;
    private int ioaFieldLength;

    private int maxTimeNoAckReceived;
    private int maxTimeNoAckSent;
    private int maxIdleTime;
    private int connectionTimeout;

    private int maxUnconfirmedIPdusReceived;
    private int maxNumOfOutstandingIPdus;

    private boolean useSharedThreadPool;

    public ConnectionSettings() {
        this.messageFragmentTimeout = 5_000;

        this.cotFieldLength = 2;
        this.commonAddressFieldLength = 2;
        this.ioaFieldLength = 3;

        this.connectionTimeout = 30_000;
        this.maxTimeNoAckReceived = 15_000;
        this.maxTimeNoAckSent = 10_000;
        this.maxIdleTime = 20_000;
        this.maxUnconfirmedIPdusReceived = 8;
        this.maxNumOfOutstandingIPdus = 12;

        this.useSharedThreadPool = false;
    }

    public ConnectionSettings(ConnectionSettings connectionSettings) {

        messageFragmentTimeout = connectionSettings.messageFragmentTimeout;

        cotFieldLength = connectionSettings.cotFieldLength;
        commonAddressFieldLength = connectionSettings.commonAddressFieldLength;
        ioaFieldLength = connectionSettings.ioaFieldLength;

        maxTimeNoAckReceived = connectionSettings.maxTimeNoAckReceived;
        maxTimeNoAckSent = connectionSettings.maxTimeNoAckSent;
        maxIdleTime = connectionSettings.maxIdleTime;
        connectionTimeout = connectionSettings.connectionTimeout;

        maxUnconfirmedIPdusReceived = connectionSettings.maxUnconfirmedIPdusReceived;
        maxNumOfOutstandingIPdus = connectionSettings.maxNumOfOutstandingIPdus;

        this.useSharedThreadPool = connectionSettings.useSharedThreadPool;
    }

    public boolean useSharedThreadPool() {
        return useSharedThreadPool;
    }

    public int getMessageFragmentTimeout() {
        return messageFragmentTimeout;
    }

    public int getCotFieldLength() {
        return cotFieldLength;
    }

    public int getCommonAddressFieldLength() {
        return commonAddressFieldLength;
    }

    public int getIoaFieldLength() {
        return ioaFieldLength;
    }

    public int getMaxTimeNoAckReceived() {
        return maxTimeNoAckReceived;
    }

    public int getMaxTimeNoAckSent() {
        return maxTimeNoAckSent;
    }

    public int getMaxIdleTime() {
        return maxIdleTime;
    }

    public int getMaxUnconfirmedIPdusReceived() {
        return maxUnconfirmedIPdusReceived;
    }

    public int getMaxNumOfOutstandingIPdus() {
        return this.maxNumOfOutstandingIPdus;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setUseSharedThreadPool(boolean useSharedThreadPool) {
        this.useSharedThreadPool = useSharedThreadPool;
    }

    public void setMessageFragmentTimeout(int messageFragmentTimeout) {
        this.messageFragmentTimeout = messageFragmentTimeout;
    }

    public void setCotFieldLength(int cotFieldLength) {
        this.cotFieldLength = cotFieldLength;
    }

    public void setCommonAddressFieldLength(int commonAddressFieldLength) {
        this.commonAddressFieldLength = commonAddressFieldLength;
    }

    public void setIoaFieldLength(int ioaFieldLength) {
        this.ioaFieldLength = ioaFieldLength;
    }

    public void setMaxTimeNoAckReceived(int maxTimeNoAckReceived) {
        this.maxTimeNoAckReceived = maxTimeNoAckReceived;
    }

    public void setMaxTimeNoAckSent(int maxTimeNoAckSent) {
        this.maxTimeNoAckSent = maxTimeNoAckSent;
    }

    public void setMaxIdleTime(int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public void setMaxUnconfirmedIPdusReceived(int maxUnconfirmedIPdusReceived) {
        this.maxUnconfirmedIPdusReceived = maxUnconfirmedIPdusReceived;
    }

    public void setMaxNumOfOutstandingIPdus(int maxNumOfOutstandingIPdus) {
        this.maxNumOfOutstandingIPdus = maxNumOfOutstandingIPdus;
    }

    public void setConnectionTimeout(int time) {
        this.connectionTimeout = time;

    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }

    public static synchronized void incremntConnectionsCounter() {
        numOpenConnections++;
    }

    public static synchronized void decrementConnectionsCounter() {
        if (--numOpenConnections == 0) {
            threadPool.shutdown();
        }
    }

}
