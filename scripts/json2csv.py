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


def build_compound_row():
    return [data.index(comp) + 1, comp['compound_id'], comp['smiles'], comp['molecular_weight'], comp['ALogP'],
            comp['molecular_formula'], comp['num_rings'], comp['image']]


def build_assay_row():
    return [assayIdNo, comp['compound_id'], assay['result_id'],
            assay['target'], assay['result'], assay['operator'], assay['value'], assay['unit']]


with open('compounds.csv', 'w', encoding='UTF8') as fc:
    with open('assay_results.csv', 'w', encoding='UTF8') as fa:
        writer_c = csv.writer(fc)
        writer_c.writerow(headers_for_compounds)
        writer_a = csv.writer(fa)
        writer_a.writerow(headers_for_assay_results)
        assayIdNo = 0
        for comp in data:
            compoundRow = build_compound_row()
            writer_c.writerow(compoundRow)
            for assay in comp['assay_results']:
                assayIdNo = assayIdNo + 1
                assayRow = build_assay_row()
                writer_a.writerow(assayRow)
