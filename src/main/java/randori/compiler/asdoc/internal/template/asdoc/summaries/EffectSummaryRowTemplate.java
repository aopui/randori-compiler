/***
 * Copyright 2013 Teoti Graphix, LLC.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * 
 * @author Michael Schmalle <mschmalle@teotigraphix.com>
 */

package randori.compiler.asdoc.internal.template.asdoc.summaries;

import java.io.File;

import org.apache.flex.compiler.definitions.IEffectDefinition;

import randori.compiler.asdoc.config.IDocConfiguration;

/**
 * @author Michael Schmalle
 * @copyright Teoti Graphix, LLC
 * @since 1.0
 */
public class EffectSummaryRowTemplate extends MetaTagSummaryRow
{
    // --------------------------------------------------------------------------
    //
    // Public :: Constants
    //
    // --------------------------------------------------------------------------

    public static final String TEMPLATE_FILE = "summary/effect-summary-row.vm";

    // --------------------------------------------------------------------------
    //
    // Constructor
    //
    // --------------------------------------------------------------------------

    /**
     * Constructor.
     */
    public EffectSummaryRowTemplate(IDocConfiguration config)
    {
        super(config);
        setTemplateFile(new File(TEMPLATE_FILE));
    }

    @Override
    protected void renderRow()
    {
        IEffectDefinition effect = (IEffectDefinition) getDefinition();
        // trigger
        String event = effect.getEvent();
        putRow("event", event);
    }
}
