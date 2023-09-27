function methodSharedValueObject() {
    var valueIncorporated = Polyglot.import('valueIncorporated');
    console.log(`#Console.log old value valueIncorporated: ${valueIncorporated.getValue()}`)
    console.log(`#Console.log update value for \'New value!\''`)
    valueIncorporated.setValue('New value!')
    console.log(`#Console.log new value valueIncorporated: ${valueIncorporated.getValue()}`)
    console.log(valueIncorporated.getValue());
}

Polyglot.export('methodSharedValueObject', methodSharedValueObject);