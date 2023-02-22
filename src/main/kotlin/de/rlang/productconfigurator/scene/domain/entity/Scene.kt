package de.rlang.productconfigurator.scene.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.ManyToOne

@Entity(name = "scene")
class Scene(
    @Id
    @SequenceGenerator(name = "scene_id_seq", sequenceName = "scene_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scene_id_seq")
    var id: Long?,
    val name: String,

    @ManyToOne
    val environment: EnvironmentEntity
)