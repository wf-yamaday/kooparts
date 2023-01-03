package org.kooparts.core.parser

import kotlinx.serialization.decodeFromString
import net.mamoe.yamlkt.Yaml
import org.kooparts.core.Workflow

fun parser(yamlText: String) = Yaml.decodeFromString<Workflow>(yamlText)
