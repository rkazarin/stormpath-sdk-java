/*
 * Copyright 2012 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stormpath.sdk.impl.group;

import com.stormpath.sdk.group.GroupMembership;
import com.stormpath.sdk.group.GroupMembershipList;
import com.stormpath.sdk.impl.ds.InternalDataStore;
import com.stormpath.sdk.impl.resource.AbstractCollectionResource;

import java.util.Map;

/**
 * @since 0.4
 */
public class DefaultGroupMembershipList extends AbstractCollectionResource<GroupMembership> implements GroupMembershipList {
    public DefaultGroupMembershipList(InternalDataStore dataStore) {
        super(dataStore);
    }

    public DefaultGroupMembershipList(InternalDataStore dataStore, Map<String, Object> properties) {
        super(dataStore, properties);
    }

    @Override
    protected Class<GroupMembership> getItemType() {
        return GroupMembership.class;
    }

    @Override
    protected GroupMembership toResource(Class<GroupMembership> resourceClass, Map<String, Object> properties) {
        return super.toResource(resourceClass, properties);
    }
}
