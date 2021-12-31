import json
import codecs
import csv

schema = json.load(codecs.open('schema.json', 'r', 'utf-8-sig'))
data = json.load(codecs.open('compounds.json', 'r', 'utf-8-sig'))

headers_for_compounds = list(schema['properties'].keys())[:-1]
headers_for_compounds.insert(0, 'id')

headers_for_assay_results = list(schema['properties']['assay_results']['properties'].keys())
headers_for_assay_results.insert(0, 'id')
headers_for_assay_results.insert(1, 'compound_id')


def build_compound_sql():
    return [str(comp['compound_id']), q_str(comp['smiles']), str(comp['molecular_weight']),
            str(comp['ALogP']), q_str(comp['molecular_formula']), str(comp['num_rings']), q_str(comp['image'])]


def build_assay_sql():
    return [str(comp['compound_id']), str(assay['result_id']),
            q_str(assay['target']), q_str(assay['result']), q_str(assay['operator']), str(assay['value']),
            q_str(assay['unit'])]


def q_str(s):
    return '\'' + s + '\''


with open('newdata.sql', 'w', encoding='UTF8') as f:
    assayIdNo = 0
    for comp in data:
        compoundRow = build_compound_sql()
        f.write(
            'INSERT INTO compound (compound_id,smiles,molecular_weight,ALogP,molecular_formula,num_rings,image) ' +
            'VALUES (' + ','.join(compoundRow) + ');\n')

        for assay in comp['assay_results']:
            assayIdNo = assayIdNo + 1
            assayRow = build_assay_sql()
            f.write('INSERT INTO assay_result (compound_id,result_id,target,result,operator,value,unit) ' +
                    'VALUES (' + ','.join(assayRow) + ');\n')
