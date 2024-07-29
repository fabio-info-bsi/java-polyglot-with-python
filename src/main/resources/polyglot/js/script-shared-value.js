function methodSharedValueObject() {
    const valueIncorporated = Polyglot.import('valueIncorporated');
    console.log(`#Console.log#methodSharedValueObject old value valueIncorporated: ${valueIncorporated.getValue()}`)
    console.log(`#Console.log#methodSharedValueObject update value for \'New value!\''`)
    valueIncorporated.setValue('New value!')
    console.log(`#Console.log#methodSharedValueObject new value valueIncorporated: ${valueIncorporated.getValue()}`)
    console.log(valueIncorporated.getValue());
}

Polyglot.export('methodShaconredValueObject', methodSharedValueObject);