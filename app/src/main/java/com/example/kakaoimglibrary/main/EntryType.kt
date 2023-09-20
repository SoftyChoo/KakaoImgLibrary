package com.example.kakaoimglibrary.main

enum class EntryType {
    ADD, REMOVE;
    companion object {
        fun from(name: String?): EntryType? {
            return EntryType.values().find {
                it.name.uppercase() == name?.uppercase()
            }
        }
    }
}