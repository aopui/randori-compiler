/***
 * Copyright 2013 Teoti Graphix, LLC.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * @author Michael Schmalle <mschmalle@teotigraphix.com>
 */

package randori.compiler.internal.codegen.js.emitter;

import java.util.Collection;

import org.apache.flex.compiler.definitions.IFunctionDefinition;
import org.apache.flex.compiler.definitions.IScopedDefinition;
import org.apache.flex.compiler.tree.as.IFunctionNode;
import org.apache.flex.compiler.tree.as.IScopedDefinitionNode;

import randori.compiler.codegen.js.IRandoriEmitter;
import randori.compiler.codegen.js.ISubEmitter;
import randori.compiler.internal.utils.MetaDataUtils;
import randori.compiler.internal.utils.RandoriUtils;

/**
 * Handles the production of the specialized footer Randori requires for
 * {@link IFunctionNode};
 * 
 * @author Michael Schmalle
 */
public class FunctionFooterEmitter extends BaseSubEmitter implements
        ISubEmitter<IFunctionNode>
{

    public FunctionFooterEmitter(IRandoriEmitter emitter)
    {
        super(emitter);
    }

    @Override
    public void emit(IFunctionNode node)
    {
        //emitInherit(node);
        writeNewline();
        writeNewline();

        emitClassName(node);
        emitDependencies(node, "Runtime", getEmitter().getModel()
                .getRuntimeDependencies());
        emitDependencies(node, "Static", getEmitter().getModel()
                .getStaticDependencies());
        emitInjectionPoints(node);
        //emitLast(node);
    }

    void emitClassName(IFunctionNode node)
    {
        // foo.bar.Baz.className = "foo.bar.Baz";
        write(node.getQualifiedName());
        write(".className = ");
        write("\"" + node.getQualifiedName() + "\"");
        writeNewline(";");
        writeNewline();
    }

    void emitDependencies(IScopedDefinitionNode node, String name,
            Collection<IScopedDefinition> dependencies)
    {
        // foo.bar.Baz.get[name]Dependencies = function () {
        //     var p;
        //     return  [];
        // };

        final String qualifiedName = MetaDataUtils.getExportQualifiedName(node
                .getDefinition());

        write(qualifiedName);
        writeNewline(".get" + name + "Dependencies = function(t) {", true);
        writeNewline("var p;");

        if (dependencies.size() > 0)
        {
            writeNewline("p = [];");
            for (IScopedDefinition type : dependencies)
            {
                String depName = RandoriUtils.toDependencyName(type);
                writeNewline("p.push('" + depName + "');");
            }
            writeNewline("return p;", false);
        }
        else
        {
            writeNewline("return [];", false);
        }

        writeNewline("};");
        writeNewline();
    }

    void emitInjectionPoints(IFunctionNode node)
    {
        IFunctionDefinition definiton = node.getDefinition();
        //        IFunctionDefinition baseDefinition = definiton
        //                .resolveBaseClass(getProject());

        //        boolean hasArgs = DefinitionUtils.hasConstructorParameters(definiton);
        //        //        boolean isValidBase = isValidBaseClasse(baseDefinition);
        //
        //        Collection<IMetaTag> propertyInjections = getEmitter().getModel()
        //                .getPropertyInjections();
        //        Collection<IMetaTag> methodInjections = getEmitter().getModel()
        //                .getMethodInjections();
        //        Collection<IMetaTag> viewInjections = getEmitter().getModel()
        //                .getViewInjections();

        // XXX TEMP until this gets worked out
        emitEmptyInjectionPoints(definiton);

        //        if (!isValidBase)
        //        {
        //            if (!hasArgs && propertyInjections.size() == 0
        //                    && methodInjections.size() == 0
        //                    && viewInjections.size() == 0)
        //            {
        //                emitEmptyInjectionPoints(definiton);
        //                return;
        //            }
        //        }

        //        write(node.getQualifiedName());
        //        writeNewline(".injectionPoints = function(t) {", true);
        //        writeNewline("var p;");
        //        writeNewline("switch (t) {", true);

        //        emitInjectionConstructor(node, hasArgs);
        //        emitInjectionProperty(node, propertyInjections, isValidBase);
        //        emitInjectionMethod(node, methodInjections, isValidBase);
        //        emitInjectionView(node, viewInjections, isValidBase);
        //        emitInjectionDefault(node, isValidBase);
        //
        //        writeNewline("}");
        //        writeNewline("return p;", false);
        //        writeNewline("};");
        //        writeNewline();
    }

    private void emitEmptyInjectionPoints(IFunctionDefinition definiton)
    {
        // native base class and no constructor injection
        // just return an empty array
        write(definiton.getQualifiedName());
        writeNewline(".injectionPoints = function(t) {", true);
        writeNewline("return [];", false);
        writeNewline("};");
    }
}
